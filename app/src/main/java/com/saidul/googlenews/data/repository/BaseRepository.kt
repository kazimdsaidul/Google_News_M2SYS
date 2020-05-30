package com.saidul.googlenews.data.repository

import com.saidul.googlenews.data.db.AppDatabase
import com.saidul.googlenews.data.network.APIService
import com.saidul.googlenews.data.network.SafeApiRequest

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-03.
 */
open class BaseRepository(
    val apiService: APIService,
    val db: AppDatabase

) : SafeApiRequest()
