package pl.borys.decide.usecase.vote.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer(
        val id:String,
        val label:String
): Parcelable