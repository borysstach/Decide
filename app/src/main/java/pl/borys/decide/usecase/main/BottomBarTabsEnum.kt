package pl.borys.decide.usecase.main

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import pl.borys.decide.R

enum class BottomBarTabsEnum(
        @StringRes val title: Int,
        @DrawableRes val icon: Int
){
    VOTE(
            R.string.tab_vote,
            R.drawable.ic_exclamation_mark
    ),
    ASK(
            R.string.tab_ask,
            R.drawable.ic_question_mark
    ),
    SOCIAL(
            R.string.tab_social,
            R.drawable.ic_people
    )
}