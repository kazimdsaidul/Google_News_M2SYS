package com.saidul.googlenews.ui.newsDetails

import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.webkit.WebViewClientCompat
import com.saidul.googlenews.R
import com.saidul.googlenews.base.BaseActivity
import com.saidul.googlenews.data.network.model.Article
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.content_news_details.*


class NewsDetailsActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val item = intent.getParcelableExtra("data") as Article

        title = item.url

        mWebView.loadUrl(item.url)

        // Enable Javascript
        // Enable Javascript
        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true

        mWebView.webViewClient = object : WebViewClientCompat() {

            override fun shouldOverrideUrlLoading(
                view: WebView, request: WebResourceRequest
            ): Boolean {
                return !request.hasGesture()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                materialProgressBar.visibility = View.GONE
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                materialProgressBar.visibility = View.VISIBLE

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

}
