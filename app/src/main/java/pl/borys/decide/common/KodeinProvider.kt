package pl.borys.decide.common

import android.support.annotation.VisibleForTesting
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import pl.borys.decide.usecase.vote.model.fakeVoteModule

object KodeinProvider {
    val TEST_TAG = "is_testing"
    var kodeinInstance =
            Kodein {
                bind<Boolean>(tag = TEST_TAG) with singleton { false }
                import(fakeVoteModule)
            }
        private set

    @VisibleForTesting()
    fun override(kodein: Kodein) {
        kodeinInstance = kodein
    }
}