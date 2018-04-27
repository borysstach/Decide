package pl.borys.decide.helper.injection

import android.arch.lifecycle.MutableLiveData
import android.support.v4.app.Fragment
import io.reactivex.Observable
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.singleton
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import pl.borys.decide.common.doNothing
import pl.borys.decide.common.viewModel.Response
import pl.borys.decide.usecase.vote.dto.VoteSheet
import pl.borys.decide.usecase.vote.model.FakeVoteSheetFactory
import pl.borys.decide.usecase.vote.model.VoteApi
import pl.borys.decide.usecase.vote.viewModel.VoteSheetsResponse
import pl.borys.decide.usecase.vote.viewModel.VoteViewModel

fun getVoteModule(
        voteSheets: List<VoteSheet> = listOf(FakeVoteSheetFactory.newVoteSheet()),
        overrides: Boolean = true
) = Kodein.Module {
    bind<VoteApi>(overrides = overrides) with singleton {
        object : VoteApi {
            override fun getSheets(): Observable<List<VoteSheet>> =
                    Observable.just(voteSheets)

            override fun vote(sheetId: String, answerId: Int): Observable<Unit> =
                    Observable.just(doNothing)

        }
    }
}

fun getVoteViewModelModule(
        voteVM: VoteViewModel? = null,
        overrides: Boolean = true
) = Kodein.Module {
    bind<VoteViewModel>(overrides = overrides) with factory { fragment: Fragment ->
        if (voteVM == null) {
            val newVoteVM = mock(VoteViewModel::class.java)
            val liveData: MutableLiveData<VoteSheetsResponse> = MutableLiveData()
            liveData.postValue(
                    Response.success(
                            listOf(
                                    FakeVoteSheetFactory.newVoteSheet()
                            )
                    ))
            `when`(newVoteVM.getVoteSheets()).thenReturn(liveData)
            newVoteVM
        } else {
            voteVM
        }
    }
}