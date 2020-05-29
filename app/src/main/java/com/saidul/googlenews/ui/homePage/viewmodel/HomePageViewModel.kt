package com.saidul.googlenews.ui.homePage.viewmodel

import androidx.lifecycle.ViewModel
import com.saidul.googlenews.data.repository.BaseRepository
import com.saidul.googlenews.ui.homePage.view.IHomePageView


class HomePageViewModel(val repository: BaseRepository) : ViewModel() {
    fun setView(iHomePageView: IHomePageView) {
        this.mView = iHomePageView
    }

    // var view: IsendMoneyVIew? = null
    var mView: IHomePageView? = null

}