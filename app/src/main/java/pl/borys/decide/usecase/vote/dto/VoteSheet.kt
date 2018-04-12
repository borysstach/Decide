package pl.borys.decide.usecase.vote.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VoteSheet(
        val id: String,
        val title: String,
        val category: String,
        val answers: List<Answer>
): Parcelable