package pl.borys.decide.helper.espresso

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import pl.borys.decide.R
import pl.borys.decide.usecase.main.BottomBarTabsEnum

object NavigationHelper {
    fun clickBottomBarTab(tab: BottomBarTabsEnum){
        onView(allOf(withId(R.id.bottom_navigation_item_title), withText(tab.title))).perform(android.support.test.espresso.action.ViewActions.click())
    }
}