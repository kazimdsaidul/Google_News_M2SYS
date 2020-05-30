package com.saidul.googlenews.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 5/30/20.
 */
object ConnectionDetector {
    fun isConnectingToInternet(context: Context): Boolean {
        val connectivity =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null) for (i in info.indices) if (info[i]
                    .state == NetworkInfo.State.CONNECTED
            ) {
                return true
            }
        }
        return false
    }
}