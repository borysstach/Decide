package pl.borys.decide.usecase.social

import android.support.v4.app.Fragment
import pl.borys.decide.R
import pl.borys.decide.common.views.BaseFragment

class SocialFragment : BaseFragment() {

    override val layout = R.layout.social_fragment

    companion object {
        fun getInstance(): Fragment = SocialFragment()
    }
}