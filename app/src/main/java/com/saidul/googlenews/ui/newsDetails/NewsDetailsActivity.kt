package com.saidul.googlenews.ui.newsDetails

import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebChromeClient.CustomViewCallback
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.saidul.googlenews.R
import com.saidul.googlenews.base.BaseActivity
import com.saidul.googlenews.data.network.model.Article
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.content_news_details.*


class NewsDetailsActivity : BaseActivity() {
    lateinit var mCustomView: View
    var mCustomViewCallback: CustomViewCallback? = null
    lateinit var mContentView: LinearLayout
    lateinit var mCustomViewContainer: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        setSupportActionBar(toolbar)
        enableToolbarBackButton()
        initilzationVIew()


    }

    private fun initilzationVIew() {
        val item = intent.getParcelableExtra("data") as Article
        title = item.url
        setupWebView(item)
    }

    private fun setupWebView(item: Article) {

        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        mWebView.loadUrl(item.url)

        val mWebChromeClient = MyWebChromeClient()
        mWebView.webChromeClient = mWebChromeClient

        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                materialProgressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                materialProgressBar.visibility = View.GONE
            }
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    inner class MyWebChromeClient : WebChromeClient() {
        var LayoutParameters = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )

        override fun onShowCustomView(
            view: View,
            callback: CustomViewCallback
        ) {
            // if a view already exists then immediately terminate the new one
            if (mCustomView != null) {
                callback.onCustomViewHidden()
                return
            }
            mContentView = findViewById<View>(R.id.activity_main) as LinearLayout
            mContentView.visibility = View.GONE
            mCustomViewContainer = FrameLayout(this@NewsDetailsActivity)
            mCustomViewContainer.layoutParams = LayoutParameters
            mCustomViewContainer.setBackgroundResource(R.color.material_on_background_disabled)
            view.layoutParams = LayoutParameters
            mCustomViewContainer.addView(view)
            mCustomView = view
            mCustomViewCallback = callback
            mCustomViewContainer.visibility = View.VISIBLE
            setContentView(mCustomViewContainer)
        }

        override fun onHideCustomView() {
            if (mCustomView == null) {
                return
            } else {
                // Hide the custom view.
                mCustomView.visibility = View.GONE
                // Remove the custom view from its container.
                mCustomViewContainer.removeView(mCustomView)

                mCustomViewContainer.visibility = View.GONE
                mCustomViewCallback?.onCustomViewHidden()
                // Show the content view.
                mContentView.visibility = View.VISIBLE
                setContentView(mContentView)
            }
        }
    }

}
