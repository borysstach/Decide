package pl.borys.decide.helper

import android.support.test.espresso.web.model.Atoms.getTitle
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v7.widget.Toolbar
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`


fun withToolbarTitle(title: CharSequence): Matcher<View> {
    return withToolbarTitle(`is`(title))
}

fun withToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<View> {
    return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {
        public override fun matchesSafely(toolbar: Toolbar): Boolean {
            return textMatcher.matches(toolbar.title)
        }

        override fun describeTo(description: Description) {
            description.appendText("with toolbar title: ")
            textMatcher.describeTo(description)
        }
    }
}