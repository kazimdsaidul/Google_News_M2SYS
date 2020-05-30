package com.saidul.googlenews.utils

import android.content.Context
import android.content.res.Configuration

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 5/30/20.
 */


class DevicesChecker {
    companion object {
        fun isTablet(ctx: Context): Boolean {
            return ctx.resources
                .configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
        }

    }
}