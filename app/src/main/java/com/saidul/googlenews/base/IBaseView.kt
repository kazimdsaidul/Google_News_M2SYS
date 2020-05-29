package com.saidul.googlenews.base

interface IBaseView {

    fun noInternetConnectionFound()
    fun showProgress()
    fun hiddenProgress()
    fun onFailure(message: String?)
}