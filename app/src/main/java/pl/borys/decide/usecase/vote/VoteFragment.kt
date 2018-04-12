package pl.borys.decide.usecase.vote

import android.support.v4.app.Fragment
import pl.borys.decide.R
import pl.borys.decide.common.views.BaseFragment

class VoteFragment : BaseFragment() {

    override val layout = R.layout.vote_fragment

    companion object {
        fun getInstance(): Fragment = VoteFragment()
    }
}