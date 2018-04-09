package pl.borys.decide.usecase.main

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import pl.borys.decide.R

enum class BottomBarTabsEnum(
        @StringRes val title: Int,
        @DrawableRes val icon: Int
){
    DECIDE(
            R.string.tab_decide,
            R.drawable.ic_exclamation_mark
    ),
    ASK(
            R.string.tab_ask,
            R.drawable.ic_question_mark
    ),
    FRIENDS(
            R.string.tab_friends,
            R.drawable.ic_people
    )
}