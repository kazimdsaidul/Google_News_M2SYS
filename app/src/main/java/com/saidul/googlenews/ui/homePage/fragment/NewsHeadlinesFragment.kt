package com.saidul.googlenews.ui.homePage.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.saidul.googlenews.R
import com.saidul.googlenews.base.BaseFragment
import com.saidul.googlenews.ui.homePage.view.HomePageFactory
import com.saidul.googlenews.ui.homePage.view.IHomePageView
import com.saidul.googlenews.ui.homePage.viewmodel.HomePageViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class NewsHeadlinesFragment : BaseFragment(), IHomePageView, KodeinAware {

    override val kodein by kodein()
    private val factory: HomePageFactory by instance()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.news_headlines_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        val viewModel =
            ViewModelProviders.of(requireActivity(), factory).get(HomePageViewModel::class.java)
        viewModel.setView(this)
    }


}
