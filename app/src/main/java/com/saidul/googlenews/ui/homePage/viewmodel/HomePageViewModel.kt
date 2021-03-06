package com.saidul.googlenews.ui.homePage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saidul.googlenews.data.network.model.Article
import com.saidul.googlenews.data.repository.AppRepository
import com.saidul.googlenews.ui.homePage.view.IHomePageView
import com.saidul.googlenews.utils.exception.ApiException
import com.saidul.googlenews.utils.exception.Coroutines
import com.saidul.googlenews.utils.exception.NoInternetException


class HomePageViewModel(val repository: AppRepository) : ViewModel() {
    fun setView(iHomePageView: IHomePageView) {
        this.mView = iHomePageView
    }

    init {
        getArticleList(false)
    }


    var listOfArticle: MutableLiveData<List<Article>> = MutableLiveData()

    private var isLoading: Boolean = false
    var mView: IHomePageView? = null


    private fun getArticleList(isNeddToRefresh: Boolean) {
        mView?.showProgress()
        Coroutines.main {
            try {
                isLoading = true
                val articles = repository.getArticleList(isNeddToRefresh)
                listOfArticle.value = articles
                isLoading = false
                mView?.hiddenProgress()

            } catch (e: ApiException) {
                mView?.hiddenProgress()
                mView?.onFailure(e.message)
            } catch (e: NoInternetException) {
                mView?.hiddenProgress()
                mView?.noInternetConnectionFound()
            }
        }
    }

    fun onRefresh() {
        getArticleList(true)

    }

}