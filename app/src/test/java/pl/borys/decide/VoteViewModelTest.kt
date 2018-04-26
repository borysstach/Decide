package pl.borys.decide

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import org.mockito.Mockito.verify
import pl.borys.decide.common.KodeinProvider
import pl.borys.decide.common.doNothing
import pl.borys.decide.common.model.emitAfter
import pl.borys.decide.common.viewModel.Response
import pl.borys.decide.helper.injection.TestKodein
import pl.borys.decide.java.extensions.mock
import pl.borys.decide.java.factory.FakeVoteSheetFactory
import pl.borys.decide.usecase.vote.dto.VoteSheet
import pl.borys.decide.usecase.vote.model.VoteApi
import pl.borys.decide.usecase.vote.viewModel.VoteSheetsResponse
import pl.borys.decide.usecase.vote.viewModel.VoteViewModel
import java.util.concurrent.TimeUnit


@RunWith(JUnit4::class)
class VoteViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun doOnStart() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
    }

    private val voteVM by lazy { VoteViewModel() }
    private val observer = mock<Observer<VoteSheetsResponse>>()

    @Test
    fun getVoteSheets_ReturnLoadResponse_WhileDataIsFetched() {
        initWithVoteSheets()
        voteVM.getVoteSheets().observeForever(observer)

        verify(observer).onChanged(Response.loading())
    }

    @Test
    fun getVoteSheets_ReturnData_AfterFetchedDelay() {
        val delay = 3L
        val data = listOf(FakeVoteSheetFactory.newVoteSheet())

        initWithVoteSheets(voteSheets = data, delay = delay)
        voteVM.getVoteSheets().observeForever(observer)
        Thread.sleep((delay + 1) * 1000)

        verify(observer).onChanged(Response.success(data))
    }

    @Test
    fun getVoteSheets_ReturnError() {
        val error = Throwable("some dangerous error")

        initWithVoteSheets(delay = 0, error = error)
        voteVM.getVoteSheets().observeForever(observer)
        Thread.sleep(1000)

        verify(observer).onChanged(Response.error(error))
    }

    private fun initWithVoteSheets(
            voteSheets: List<VoteSheet> = listOf(FakeVoteSheetFactory.newVoteSheet()),
            delay: Long = 3,
            error: Throwable? = null
    ) {
        val observable =
                if (error == null) {
                    Observable.just(voteSheets).emitAfter(delay, TimeUnit.SECONDS)
                } else {
                    Observable.error(error)
                }
        KodeinProvider.override(TestKodein.getWith(
                getVoteSheetsModuleWith(observable)
        ))
    }

    private fun getVoteSheetsModuleWith(observable: Observable<List<VoteSheet>>): Kodein.Module =
            Kodein.Module {
                bind<VoteApi>(overrides = true) with singleton {
                    object : VoteApi {
                        override fun getSheets() =
                                observable

                        override fun vote(sheetId: String, answerId: Int) =
                                Observable.just(doNothing)
                    }
                }
            }
}