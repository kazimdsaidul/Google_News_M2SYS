package com.saidul.googlenews.ui.homePage.view

import com.saidul.googlenews.appController.AllConstant
import com.saidul.googlenews.data.db.AppDatabase
import com.saidul.googlenews.data.network.APIService
import com.saidul.googlenews.data.network.model.ReposeHeadlines
import com.saidul.googlenews.data.repository.BaseRepository

class AppRepository(apiService: APIService, db: AppDatabase) :
    BaseRepository(apiService, db) {

    suspend fun getHeadlinesData(): ReposeHeadlines {
        return apiRequest {
            apiService.getHeadlinesData(
                AllConstant.KEY_COUNTRY,
                AllConstant.KEY_API_KEY
            )
        }
    }
}
