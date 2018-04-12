package pl.borys.decide

import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pl.borys.decide.helper.espresso.hasText
import pl.borys.decide.helper.factory.FakeVoteSheetFactory
import pl.borys.decide.helper.injection.getTestKodeinWith
import pl.borys.decide.helper.injection.getVoteModule
import pl.borys.decide.helper.testRules.VoteFragmentTestRule


@RunWith(AndroidJUnit4::class)
@LargeTest
class VoteFragmentTest {
    private val TEST_TITLE_1 = "VoteFragmentTestTitle1"
    private val TEST_TITLE_2 = "VoteFragmentTestTitle2"

    @get:Rule
    var activityRule = VoteFragmentTestRule(getTestKodeinWith(
            getVoteModule(listOf(
                    FakeVoteSheetFactory.newVoteSheet(title = TEST_TITLE_1),
                    FakeVoteSheetFactory.newVoteSheet(title = TEST_TITLE_2)
            ))
    ))

    @Test
    fun message_shouldShow_TestTitle() {
        R.id.message hasText listOf(TEST_TITLE_1, TEST_TITLE_2).toString()
    }

}