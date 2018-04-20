package pl.borys.decide.helper.injection

import org.kodein.di.Kodein

object TestKodein {

    fun get() = Kodein {
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