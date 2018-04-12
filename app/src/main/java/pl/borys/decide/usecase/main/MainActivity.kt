package pl.borys.decide.usecase.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import pl.borys.decide.R


class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initBottomBar()
        navController = NavController(savedInstanceState, supportFragmentManager)
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
            setOnTabSelectedListener(bottomBarSelectListener)
        }
    }

    private val bottomBarSelectListener = AHBottomNavigation.OnTabSelectedListener { position, wasSelected ->
        navController?.switchTab(position)
        true
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let {
            navController?.onSaveInstanceState(it)
        }
    }
}
