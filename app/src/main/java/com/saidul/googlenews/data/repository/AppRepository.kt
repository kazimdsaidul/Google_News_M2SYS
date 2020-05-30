package com.saidul.googlenews.data.repository

import com.saidul.googlenews.appController.AllConstant
import com.saidul.googlenews.data.db.AppDatabase
import com.saidul.googlenews.data.network.APIService
import com.saidul.googlenews.data.network.model.Article
import com.saidul.googlenews.data.network.model.ReposeHeadlines

class AppRepository(apiService: APIService, db: AppDatabase) : BaseRepository(apiService, db) {


    private suspend fun getHeadlinesDataFromSever(): ReposeHeadlines {
        return apiRequest {
            apiService.getHeadlinesData(
                AllConstant.KEY_COUNTRY,
                AllConstant.KEY_API_KEY
            )
        }
    }

    suspend fun getArticleList(neddToRefresh: Boolean): List<Article>? {
        val localData = db.getUserDao().getAllArticle()
        if (localData.isEmpty() || neddToRefresh) {
            val articles = getHeadlinesDataFromSever().articles
            db.getUserDao().clearArticleList()
            db.getUserDao().insertArticleList(articles)
            return articles
        }
        return localData
    }


}
