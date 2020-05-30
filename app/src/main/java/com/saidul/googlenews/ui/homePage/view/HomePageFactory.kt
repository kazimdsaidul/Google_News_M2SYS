package com.saidul.googlenews.ui.homePage.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saidul.googlenews.data.repository.AppRepository
import com.saidul.googlenews.ui.homePage.viewmodel.HomePageViewModel

class HomePageFactory(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomePageViewModel(
            repository
        ) as T
    }

}