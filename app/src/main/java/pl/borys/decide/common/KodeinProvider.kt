package pl.borys.decide.common

import android.support.annotation.VisibleForTesting
import org.kodein.di.Kodein
import pl.borys.decide.usecase.vote.model.fakeVoteModule

object KodeinProvider {
    var kodeinInstance = Kodein {
        import(fakeVoteModule)
    }
        private set

    @VisibleForTesting()
    fun override(kodein: Kodein) {
        kodeinInstance = kodein
    }
}