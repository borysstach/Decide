package pl.borys.decide

import android.arch.lifecycle.MutableLiveData
import android.support.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.kodein.di.Kodein
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import pl.borys.decide.common.viewModel.Response
import pl.borys.decide.helper.espresso.assertIsDisplayed
import pl.borys.decide.helper.espresso.assertIsNotDisplayed
import pl.borys.decide.helper.espresso.hasText
import pl.borys.decide.helper.factory.FakeVoteSheetFactory
import pl.borys.decide.helper.injection.TestKodein
import pl.borys.decide.helper.injection.getVoteViewModelModule
import pl.borys.decide.helper.testRules.VoteFragmentTestRule
import pl.borys.decide.usecase.vote.viewModel.VoteSheetsResponse
import pl.borys.decide.usecase.vote.viewModel.VoteViewModel

@LargeTest
class VoteFragmentTest {
    private val TEST_TITLE_1 = "VoteFragmentTestTitle1"
    private val TEST_TITLE_2 = "VoteFragmentTestTitle2"
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
        voteSheetsLiveData.postValue(Response.loading())
        R.id.loader.assertIsDisplayed()
    }

    @Test
    fun should_HideLoader_AfterFetchDataDelay() {
        voteSheetsLiveData.postValue(Response.success(listOf(FakeVoteSheetFactory.newVoteSheet())))
        R.id.loader.assertIsNotDisplayed()
    }

    @Test
    fun should_ShowTitle_AfterFetchDataDelay() {
        voteSheetsLiveData.postValue(
                Response.success(
                        listOf(
                                FakeVoteSheetFactory.newVoteSheet(title = TEST_TITLE_1),
                                FakeVoteSheetFactory.newVoteSheet(title = TEST_TITLE_2)
                        )
                )
        )
        R.id.message hasText listOf(TEST_TITLE_1, TEST_TITLE_2).toString()
    }

}