package pl.borys.decide.usecase.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import pl.borys.decide.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomBar()
    }

    private fun initBottomBar() {
        bottom_bar.apply {
            BottomBarTabsEnum.values()
                    .forEach { item ->
                        addItem(
                                AHBottomNavigationItem(
                                        item.title,
                                        item.icon,
                                        0
                                )
                        )
                    }
            setOnTabSelectedListener(onNavigationItemSelectedListener)
        }
    }

    private val onNavigationItemSelectedListener = AHBottomNavigation.OnTabSelectedListener { position, wasSelected ->
        when (position) {
            BottomBarTabsEnum.VOTE.ordinal -> {
                message.setText(BottomBarTabsEnum.VOTE.title) //TODO: change to decide fragment
                return@OnTabSelectedListener true
            }
            BottomBarTabsEnum.ASK.ordinal -> {
                message.setText(BottomBarTabsEnum.ASK.title) //TODO: change to ask fragment
                return@OnTabSelectedListener true
            }
            BottomBarTabsEnum.SOCIAL.ordinal -> {
                message.setText(BottomBarTabsEnum.SOCIAL.title) //TODO: change to friends fragment
                return@OnTabSelectedListener true
            }
        }
        false
    }
}
