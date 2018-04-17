package pl.borys.decide.usecase.vote.model

import io.reactivex.Observable
import pl.borys.decide.usecase.vote.dto.VoteSheet

interface VoteApi {
    fun getSheets(): Observable<List<VoteSheet>>
    fun vote(sheetId: String, answerId:Int): Observable<Unit>
}