package pl.borys.decide.usecase.vote.model

import pl.borys.decide.usecase.vote.dto.Answer
import pl.borys.decide.usecase.vote.dto.VoteSheet
import java.util.*

object FakeVoteSheetFactory {
    fun newVoteSheet() = VoteSheet(
            id= UUID.randomUUID().toString(),
            category = "Food",
            title = "awesomeTitle",
            answers = listOf(
                    Answer(
                            id = UUID.randomUUID().toString(),
                            label = "First One!"
                    ),
                    Answer(
                            id = UUID.randomUUID().toString(),
                            label = "Second One!"
                    )
            )
    )
}