package pl.borys.decide

import android.support.test.runner.AndroidJUnit4
import android.support.test.filters.LargeTest
import org.junit.runner.RunWith
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import pl.borys.decide.helper.NavigationHelper
import pl.borys.decide.helper.hasText
import pl.borys.decide.usecase.main.BottomBarTabsEnum
import pl.borys.decide.usecase.main.MainActivity


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun message_changeTo_Decide() {
       NavigationHelper.clickBottomBarTab(BottomBarTabsEnum.VOTE)
        R.id.message hasText R.string.tab_vote
    }
    @Test
    fun message_changeTo_Ask() {
        NavigationHelper.clickBottomBarTab(BottomBarTabsEnum.ASK)
        R.id.message hasText R.string.tab_ask
    }

    @Test
    fun message_changeTo_Friends() {
        NavigationHelper.clickBottomBarTab(BottomBarTabsEnum.SOCIAL)
        R.id.message hasText R.string.tab_social
    }
}