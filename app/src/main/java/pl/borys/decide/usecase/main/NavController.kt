package pl.borys.decide.usecase.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import pl.borys.decide.R
import pl.borys.decide.usecase.ask.AskFragment
import pl.borys.decide.usecase.social.SocialFragment
import pl.borys.decide.usecase.vote.VoteFragment

class NavController(savedInstanceState: Bundle?, supportFragmentManager: FragmentManager) : FragNavController.RootFragmentListener {

    private var fragNavController: FragNavController? = null

    init {
        val builder = FragNavController.newBuilder(savedInstanceState, supportFragmentManager, R.id.fragments_container)
        builder.rootFragments(
                listOf(
                        getRootFragment(BottomBarTabsEnum.VOTE.ordinal),
                        getRootFragment(BottomBarTabsEnum.ASK.ordinal),
                        getRootFragment(BottomBarTabsEnum.SOCIAL.ordinal)
                )
        )
        builder.rootFragmentListener(this, 3)
        fragNavController = builder.build()
    }

    fun switchTab(position: Int){
        fragNavController?.switchTab(position)
    }

    override fun getRootFragment(index: Int): Fragment {
        return when (index) {
            BottomBarTabsEnum.VOTE.ordinal -> {
                VoteFragment.getInstance()
            }
            BottomBarTabsEnum.ASK.ordinal -> {
                AskFragment.getInstance()
            }
            BottomBarTabsEnum.SOCIAL.ordinal -> {
                SocialFragment.getInstance()
            }
            else -> throw IllegalStateException("Need to send an index that we know")
        }
    }

    fun onSaveInstanceState(outState: Bundle) {
        fragNavController?.onSaveInstanceState(outState)
    }
}