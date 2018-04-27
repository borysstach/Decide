package pl.borys.decide.helper.testRules

import android.support.test.rule.ActivityTestRule
import org.kodein.di.Kodein
import pl.borys.decide.common.KodeinProvider
import pl.borys.decide.helper.injection.TestKodein
import pl.borys.decide.usecase.main.MainActivity

open class MainActivityTestRule(
       private val kodein: Kodein = TestKodein.get()
) : ActivityTestRule<MainActivity>(MainActivity::class.java) {

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()
        KodeinProvider.override(kodein)
    }
}