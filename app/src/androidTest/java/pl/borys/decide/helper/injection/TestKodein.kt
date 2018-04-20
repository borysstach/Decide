package pl.borys.decide.helper.injection

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import pl.borys.decide.common.KodeinProvider

object TestKodein {

    fun get() = Kodein {
        bind<Boolean>(tag = KodeinProvider.TEST_TAG) with singleton { true }
        import(getVoteModule(overrides = false), allowOverride = true)
        import(getVoteViewModelModule(overrides = false), allowOverride = true)
    }

    fun getWith(vararg module: Kodein.Module) = Kodein {
        extend(get(), allowOverride = true)
        module.forEach {
            import(module = it, allowOverride = true)
        }
    }
}