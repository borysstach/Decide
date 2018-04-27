package pl.borys.decide.java.factory

import pl.borys.decide.usecase.vote.dto.Answer
import pl.borys.decide.usecase.vote.dto.VoteSheet
import java.util.*

object FakeVoteSheetFactory {

    val CATEGORY = "TestFood"
    val TITLE = "awesomeTestTitle"
    val FIRST_ANSWER = "First Test One!"
    val SECOND_ANSWER = "Second Test One!"

    fun newVoteSheet(
            category: String = CATEGORY,
            title: String = TITLE,
            answers: List<Answer> = listOf(newAnswer(FIRST_ANSWER), newAnswer(SECOND_ANSWER))
    ) = VoteSheet(
            id = UUID.randomUUID().toString(),
            category = category,
            title = title,
            answers = answers
    )

    fun newAnswer(label: String = FIRST_ANSWER) =
            Answer(
                    id = UUID.randomUUID().toString(),
                    label = label
            )
}