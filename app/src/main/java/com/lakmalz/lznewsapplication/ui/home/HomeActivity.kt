package com.lakmalz.lznewsapplication.ui.home

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.lakmalz.lznewsapplication.R
import com.lakmalz.lznewsapplication.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lakmalz.lznewsapplication.ui.home.customnews.CustomNewsFragment
import com.lakmalz.lznewsapplication.ui.home.headline.HeadlineFragment
import com.lakmalz.lznewsapplication.ui.home.headline.HeadlinesViewModel
import com.lakmalz.lznewsapplication.ui.home.profile.ProfileFragment


class HomeActivity : BaseActivity() {

    private  var selectedViewId: Int = 0
    private lateinit var headlineFragment: HeadlineFragment
    private lateinit var customeNewsFragment: CustomNewsFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var appbar: ActionBar

    private val mViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        appbar = this!!.supportActionBar!!

        initUI()
        loadFragment(HeadlineFragment.newInstance(), getString(R.string.title_headline))
    }

    private fun loadFragment(fragment: Fragment, title: String) {
        appbar.title = title
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    private fun initUI() {

        nav_view.setOnNavigationItemSelectedListener {
            if (selectedViewId == it.itemId)
                return@setOnNavigationItemSelectedListener false

            when (it.itemId) {
                R.id.navigation_headline -> {
                    loadFragment(HeadlineFragment.newInstance(), getString(R.string.title_headlines))
                }
                R.id.navigation_news -> {
                    if (mViewModel.getUserData().userName != null) {
                        loadFragment(CustomNewsFragment.newInstance(), getString(R.string.title_news))
                    }
                }
                R.id.navigation_profile -> {
                    loadFragment(ProfileFragment.newInstance(), getString(R.string.title_ptofile))
                }

            }
            selectedViewId = it.itemId
            it.isChecked = true
            false
        }

    }
}
