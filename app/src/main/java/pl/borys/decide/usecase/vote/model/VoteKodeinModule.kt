package pl.borys.decide.usecase.vote.model

import io.reactivex.Observable
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import pl.borys.decide.common.doNothing
import pl.borys.decide.common.model.emitAfter
import pl.borys.decide.usecase.vote.dto.VoteSheet
import java.util.concurrent.TimeUnit


val fakeVoteModule = Kodein.Module {
    bind<VoteApi>() with singleton {
        object : VoteApi {
            override fun getSheets(): Observable<List<VoteSheet>> =
                    Observable.just(
                            listOf(
                                    FakeVoteSheetFactory.newVoteSheet(),
                                    FakeVoteSheetFactory.newVoteSheet()
                            )
                    ).emitAfter(3, TimeUnit.SECONDS)
            override fun vote(sheetId: String, answerId: Int): Observable<Unit> =
                    Observable.just(doNothing)

        }
    }
}