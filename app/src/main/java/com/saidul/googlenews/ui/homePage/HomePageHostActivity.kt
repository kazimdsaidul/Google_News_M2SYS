package com.saidul.googlenews.ui.homePage


import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.saidul.googlenews.R
import com.saidul.googlenews.base.BaseActivity
import com.saidul.googlenews.ui.homePage.fragment.NewsHeadlinesFragment
import com.saidul.googlenews.ui.homePage.view.HomePageFactory
import com.saidul.googlenews.ui.homePage.view.IHomePageView
import com.saidul.googlenews.ui.homePage.viewmodel.HomePageViewModel
import kotlinx.android.synthetic.main.activity_homepage.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomePageHostActivity : BaseActivity(), IHomePageView, KodeinAware {

    override val kodein by kodein()

    private val factory: HomePageFactory by instance<HomePageFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        setupToolbar()

        initViewModel()

        if (savedInstanceState == null) {
            addNewsHeadlinesFragment()
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        setToolbar("Google News")
        disenableToolbarBackButton()
    }

    private fun addNewsHeadlinesFragment() {
        val beneficiaryFragment = NewsHeadlinesFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.container, beneficiaryFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun initViewModel() {
        val viewModel = ViewModelProviders.of(this, factory).get(HomePageViewModel::class.java)
        viewModel.setView(this)
    }

    override fun onBackPressed() {
        finish()
    }
}
