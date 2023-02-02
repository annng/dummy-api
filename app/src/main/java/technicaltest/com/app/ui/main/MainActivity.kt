package technicaltest.com.app.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import technicaltest.com.app.R
import technicaltest.com.app.databinding.ActivityMainBinding
import technicaltest.com.app.ui.base.BaseActivity
import technicaltest.com.app.ui.main.child.like.LikeFragment

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var isFirstTime = true

    private val viewBinding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        initBottomNavigation()

        //open first page
        viewBinding.bottomNavigation.selectedItemId = R.id.menu_user
    }

    private fun initBottomNavigation(){
        viewBinding.bottomNavigation.setOnNavigationItemSelectedListener(this)
        viewBinding.bottomNavigation.itemIconTintList = null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // prevent reload when double click in same page
        var menuSelected = -1
        if (!isFirstTime) menuSelected =
            viewBinding.bottomNavigation.selectedItemId else isFirstTime = false

        when (item.itemId) {
            menuSelected -> return false
            R.id.menu_user -> {
                setIconNavigation(item, R.drawable.ic_user_fill)
                replaceFragment(LikeFragment(), item)
                return true
            }
        }

        return false
    }

    private fun replaceFragment(fragment: Fragment, item: MenuItem) {
        try {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameContainer, fragment)
            transaction.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setIconNavigation(menu: MenuItem, iconActive: Int) {
        setIconDefault()
        viewBinding.textFragmentTitle.text = menu.title
        menu.setIcon(iconActive)
    }

    private fun setIconDefault() {
        viewBinding.bottomNavigation.menu.getItem(0).setIcon(R.drawable.ic_user)
        viewBinding.bottomNavigation.menu.getItem(1).setIcon(R.drawable.ic_post)
        viewBinding.bottomNavigation.menu.getItem(2).setIcon(R.drawable.ic_like)
    }
}