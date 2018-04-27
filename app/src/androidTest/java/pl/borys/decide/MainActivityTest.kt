package pl.borys.decide

import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pl.borys.decide.helper.espresso.NavigationHelper
import pl.borys.decide.helper.espresso.assertIsDisplayed
import pl.borys.decide.helper.testRules.MainActivityTestRule
import pl.borys.decide.usecase.main.BottomBarTabsEnum


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityRule = MainActivityTestRule()

    @Test
    fun should_changeTabTo_Decide() {
        NavigationHelper.clickBottomBarTab(BottomBarTabsEnum.VOTE)
        R.id.vote_container.assertIsDisplayed()
    }

    @Test
    fun should_changeTabTo_Ask() {
        NavigationHelper.clickBottomBarTab(BottomBarTabsEnum.ASK)
        R.id.ask_container.assertIsDisplayed()
    }

    @Test
    fun should_changeTabTo_Friends() {
        NavigationHelper.clickBottomBarTab(BottomBarTabsEnum.SOCIAL)
        R.id.social_container.assertIsDisplayed()
    }
}