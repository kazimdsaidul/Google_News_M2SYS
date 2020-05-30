package com.saidul.googlenews.ui.homePage.fragment

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.saidul.googlenews.R
import com.saidul.googlenews.base.BaseFragment
import com.saidul.googlenews.data.network.model.Article
import com.saidul.googlenews.ui.homePage.fragment.model.ArticleItem
import com.saidul.googlenews.ui.homePage.view.HomePageFactory
import com.saidul.googlenews.ui.homePage.view.IHomePageView
import com.saidul.googlenews.ui.homePage.viewmodel.HomePageViewModel
import com.saidul.googlenews.ui.newsDetails.NewsDetailsActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.news_headlines_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class NewsHeadlinesFragment : BaseFragment(), IHomePageView, SwipeRefreshLayout.OnRefreshListener,
    KodeinAware {

    override val kodein by kodein()
    private val factory: HomePageFactory by instance()
    lateinit var gridLayoutManager: GridLayoutManager


    lateinit var viewModel: HomePageViewModel


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
        initView()
        initViewModel()


    }

    private fun initView() {
        swipLayout.setOnRefreshListener(this)
        if (isTablet(requireActivity().applicationContext)) {
            gridLayoutManager = GridLayoutManager(requireContext().applicationContext, 2)
        } else {
            gridLayoutManager = GridLayoutManager(requireContext().applicationContext, 1)
        }


    }

    private fun initViewModel() {
        viewModel =
            ViewModelProviders.of(requireActivity(), factory).get(HomePageViewModel::class.java)
        viewModel.setView(this)
        viewModel.listOfArticle.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })
    }


    private fun initRecyclerView(quoteItem: List<Article>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem.toImageItem())
        }
        recyclerview.apply {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = mAdapter
        }

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d("-----", "end")
                    val totalItemCount = recyclerView.layoutManager!!.itemCount
                    //viewModel.addOnScroll(totalItemCount);
                }
            }
        })

    }

    private fun List<Article>.toImageItem(): List<ArticleItem> {
        return this.map {
            ArticleItem(it, object : ArticleItem.OnLikeClickedListener {
                override fun onLikeClicked(item: Article) {
                    val intent =
                        Intent(activity?.applicationContext, NewsDetailsActivity::class.java)
                    intent.putExtra("data", item)
                    startActivity(intent)
                }
            })
        }
    }


    override fun showProgress() {
        super.showProgress()
        swipLayout.isRefreshing = true
        recyclerview.visibility = View.INVISIBLE
        materialProgressBarHome.visibility = View.VISIBLE

    }

    override fun hiddenProgress() {
        super.hiddenProgress()
        // progressBar.visibility = View.INVISIBLE
        swipLayout.isRefreshing = false
        recyclerview.visibility = View.VISIBLE
        materialProgressBarHome.visibility = View.GONE


    }

    override fun onRefresh() {
        viewModel.onRefresh()

    }

    fun isTablet(ctx: Context): Boolean {
        return ctx.resources
            .configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }


}
