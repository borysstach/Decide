package pl.borys.decide.usecase.ask

import android.support.v4.app.Fragment
import pl.borys.decide.R
import pl.borys.decide.common.views.BaseFragment

class AskFragment : BaseFragment() {

    override val layout = R.layout.ask_fragment

    companion object {
        fun getInstance(): Fragment = AskFragment()
    }
}