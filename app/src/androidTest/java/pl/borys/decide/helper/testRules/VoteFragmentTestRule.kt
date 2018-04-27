package pl.borys.decide.helper.testRules

import org.kodein.di.Kodein
import pl.borys.decide.helper.espresso.NavigationHelper
import pl.borys.decide.helper.injection.TestKodein
import pl.borys.decide.usecase.main.BottomBarTabsEnum

class VoteFragmentTestRule(kodein: Kodein = TestKodein.get()) : MainActivityTestRule(kodein) {
    override fun afterActivityLaunched() {
        super.afterActivityLaunched()
        NavigationHelper.clickBottomBarTab(BottomBarTabsEnum.VOTE)
    }
}