package com.saidul.googlenews.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.saidul.googlenews.R
import com.saidul.googlenews.base.BaseActivity
import com.saidul.googlenews.ui.homePage.HomePageHostActivity

class SplashScreenActivity : BaseActivity() {

    private val SPLASH_DISPLAY_LENGTH = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        initialization()
    }

    private fun initialization() {
        Handler().postDelayed({
            val mainIntent = Intent(this, HomePageHostActivity::class.java)
            this.startActivity(mainIntent)
            this.finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}