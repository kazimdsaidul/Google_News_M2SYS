package com.saidul.googlenews.base

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.saidul.googlenews.appController.AppController


/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-08.
 */
open class BaseActivity : AppCompatActivity(), IBaseView {
    val class_Name: String = this.javaClass.simpleName

    fun setToolbar(title: String, color: Int) {
        assert(supportActionBar != null)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            val spannablerTitle = SpannableString(title)
            spannablerTitle.setSpan(
                ForegroundColorSpan(color),
                0,
                spannablerTitle.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            supportActionBar!!.title = spannablerTitle
        }
    }

    fun setToolbar(title: String) {
        assert(supportActionBar != null)
        if (supportActionBar != null) {
            supportActionBar!!.title = title
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    fun enableToolbarBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun disenableToolbarBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
    }


    fun setToolbarWithOutBack(title: String) {
        assert(supportActionBar != null)
        if (supportActionBar != null) {
            supportActionBar!!.title = title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun hideUserKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun hiddenSoftKeyboard() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    open fun getApp(): AppController {
        return this.application as AppController
    }

    override fun noInternetConnectionFound() {

    }

    override fun showProgress() {
    }

    override fun hiddenProgress() {
    }

    override fun onFailure(message: String?) {
    }


}