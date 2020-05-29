package com.saidul.googlenews.appController

import android.app.Application
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.saidul.googlenews.data.db.AppDatabase
import com.saidul.googlenews.data.network.APIService
import com.saidul.googlenews.data.network.interceptor.HeaderTokenInterceptor
import com.saidul.googlenews.data.network.interceptor.NetworkConnectionInterceptor
import com.saidul.googlenews.ui.homePage.view.AppRepository
import com.saidul.googlenews.ui.homePage.view.HomePageFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.BuildConfig
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by Android on 12/1/2015.
 */
class AppController : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        initilizationDI()
        initilizationLogger()
    }

    private fun Kodein.MainBuilder.initilizationDI() {
        import(androidXModule(this@AppController))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { HeaderTokenInterceptor(instance()) }
        bind() from singleton { OkHttpProfilerInterceptor() }
        bind() from singleton { APIService(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { AppRepository(instance(), instance()) }


        bind() from provider { HomePageFactory(instance()) }
    }

    private fun initilizationLogger() {
        Logger.addLogAdapter(AndroidLogAdapter())

        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }


}
