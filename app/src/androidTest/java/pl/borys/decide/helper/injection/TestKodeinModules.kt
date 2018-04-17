package pl.borys.decide.helper.injection

import io.reactivex.Observable
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import pl.borys.decide.common.doNothing
import pl.borys.decide.usecase.vote.dto.VoteSheet
import pl.borys.decide.usecase.vote.model.FakeVoteSheetFactory
import pl.borys.decide.usecase.vote.model.VoteApi

fun getVoteModule(
        voteSheets: List<VoteSheet> = listOf(FakeVoteSheetFactory.newVoteSheet()),
        overrides: Boolean = true
) = Kodein.Module {
    bind<VoteApi>(overrides = overrides) with singleton {
        object : VoteApi {
            override fun getSheets(): Observable<List<VoteSheet>> =
                    Observable.just(voteSheets)

            override fun vote(sheetId: String, answerId: Int): Observable<Unit> =
                    Observable.just(doNothing)

        }
    }
}

fun getTestKodein() = Kodein {
    import(getVoteModule(overrides = false), allowOverride = true)
}

fun getTestKodeinWith(vararg module: Kodein.Module) = Kodein {
    extend(getTestKodein(), allowOverride = true)
    module.forEach {
        import(module = it, allowOverride = true)
    }
}