package com.saidul.googlenews.data.network

import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.saidul.googlenews.BuildConfig
import com.saidul.googlenews.data.network.interceptor.NetworkConnectionInterceptor
import com.saidul.googlenews.data.network.model.ReposeHeadlines
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 7/29/18.
 */
interface APIService {

    @GET("top-headlines")
    suspend fun getHeadlinesData(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<ReposeHeadlines>


    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): APIService {

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                okkHttpclient.addInterceptor(logging)
                okkHttpclient.addInterceptor(OkHttpProfilerInterceptor())
            }


            return Retrofit.Builder()
                .client(okkHttpclient.build())
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
    }


}