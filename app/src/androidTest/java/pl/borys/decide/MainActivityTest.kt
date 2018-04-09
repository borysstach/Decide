package pl.borys.decide

import android.os.SystemClock
import android.support.test.runner.AndroidJUnit4
import android.support.test.filters.LargeTest
import org.junit.runner.RunWith
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import pl.borys.decide.helper.assertTextIsDisplayed
import pl.borys.decide.helper.assertTextIsSelected
import pl.borys.decide.helper.click
import pl.borys.decide.helper.hasText


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun message_changeTo_Decide() {
        R.id.navigation_decide.click()
        R.id.message hasText R.string.tab_decide
    }
    @Test
    fun message_changeTo_Ask() {
        R.id.navigation_ask.click()
        R.id.message hasText R.string.tab_ask
    }

    @Test
    fun message_changeTo_Friends() {
        R.id.navigation_friends.click()
        R.id.message hasText R.string.tab_friends
    }
}