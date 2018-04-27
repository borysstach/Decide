package pl.borys.decide

import android.arch.lifecycle.MutableLiveData
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import pl.borys.decide.common.viewModel.Response
import pl.borys.decide.helper.espresso.assertIsDisplayed
import pl.borys.decide.helper.espresso.assertIsNotDisplayed
import pl.borys.decide.helper.espresso.hasText
import pl.borys.decide.helper.injection.TestKodein
import pl.borys.decide.helper.injection.getVoteViewModelModule
import pl.borys.decide.helper.testRules.VoteFragmentTestRule
import pl.borys.decide.java.factory.FakeVoteSheetFactory
import pl.borys.decide.usecase.vote.viewModel.VoteSheetsResponse
import pl.borys.decide.usecase.vote.viewModel.VoteViewModel

@RunWith(AndroidJUnit4::class)
@LargeTest
class VoteFragmentTest {

    private val mockViewModel = mock(VoteViewModel::class.java)
    private val voteSheetsLiveData: MutableLiveData<VoteSheetsResponse> = MutableLiveData()

    private val voteViewModule: Kodein.Module
        get() {
            `when`(mockViewModel.getVoteSheets()).thenReturn(voteSheetsLiveData)
            return getVoteViewModelModule(mockViewModel, overrides = true)
        }

    @get:Rule
    var activityRule = VoteFragmentTestRule(TestKodein.getWith(voteViewModule))

    @Test
    fun should_ShowLoader_OnStart() {
        voteSheetsLiveData.postValue(
                Response.loading()
        )
        R.id.loader.assertIsDisplayed()
    }

    @Test
    fun should_HideLoader_AfterFetchDataDelay() {
        voteSheetsLiveData.postValue(
                Response.success(
                        listOf(FakeVoteSheetFactory.newVoteSheet())
                )
        )
        R.id.loader.assertIsNotDisplayed()
    }

    @Test
    fun should_ShowTitle_AfterFetchDataDelay() {
        val testTitle1 = "VoteFragmentTestTitle1"
        val testTitle2 = "VoteFragmentTestTitle2"
        voteSheetsLiveData.postValue(
                Response.success(
                        listOf(
                                FakeVoteSheetFactory.newVoteSheet(title = testTitle1),
                                FakeVoteSheetFactory.newVoteSheet(title = testTitle2)
                        )
                )
        )
        R.id.message hasText listOf(testTitle1, testTitle2).toString()
    }

}