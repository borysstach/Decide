package pl.borys.decide

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_decide -> {
                message.setText(R.string.tab_decide)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_ask -> {
                message.setText(R.string.tab_ask)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_friends -> {
                message.setText(R.string.tab_friends)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
