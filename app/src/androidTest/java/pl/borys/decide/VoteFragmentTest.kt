package pl.borys.decide

import android.os.SystemClock
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import pl.borys.decide.common.doNothing
import pl.borys.decide.common.model.emitAfter
import pl.borys.decide.helper.espresso.hasText
import pl.borys.decide.helper.factory.FakeVoteSheetFactory
import pl.borys.decide.helper.injection.getTestKodeinWith
import pl.borys.decide.helper.testRules.VoteFragmentTestRule
import pl.borys.decide.usecase.vote.dto.VoteSheet
import pl.borys.decide.usecase.vote.model.VoteApi
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
@LargeTest
class VoteFragmentTest {
    private val TEST_TITLE_1 = "VoteFragmentTestTitle1"
    private val TEST_TITLE_2 = "VoteFragmentTestTitle2"
    private val DELAY = 3

    private val voteModule = Kodein.Module {
        bind<VoteApi>(overrides = true) with singleton {
            object : VoteApi {
                override fun getSheets(): Observable<List<VoteSheet>> =
                        Observable.just(
                                listOf(
                                        FakeVoteSheetFactory.newVoteSheet(title = TEST_TITLE_1),
                                        FakeVoteSheetFactory.newVoteSheet(title = TEST_TITLE_2)
                                )
                        ).emitAfter(3, TimeUnit.SECONDS)

                override fun vote(sheetId: String, answerId: Int): Observable<Unit> =
                        Observable.just(doNothing)

            }
        }
    }

    @get:Rule
    var activityRule = VoteFragmentTestRule(getTestKodeinWith(
            voteModule
    ))

    @Test
    fun message_shouldShow_Loading() {
        R.id.message hasText "Loading..." //TODO: get this from repository
    }

    @Test
    fun message_shouldShow_TitleAfterLoadingDelay() {
        SystemClock.sleep((DELAY + 1) * 1000L)
        R.id.message hasText listOf(TEST_TITLE_1, TEST_TITLE_2).toString()
    }

}