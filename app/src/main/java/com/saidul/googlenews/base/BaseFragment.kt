package com.saidul.googlenews.base

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment(), IView {
    override fun noInternetConnectionFound() {

    }

    override fun showProgress() {


    }

    override fun hiddenProgress() {

    }


    override fun onFailure(message: String?) {

    }


}