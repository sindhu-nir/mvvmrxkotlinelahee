package com.btracsolutions.mvvmrxkotlinelahee

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.btracsolutions.mvvmrxkotlinelahee.activity.Authentication.login.LoginActivity
import com.btracsolutions.mvvmrxkotlinelahee.activity.HomeActivity
import com.btracsolutions.mvvmrxkotlinelahee.preference.PrefKey
import com.btracsolutions.mvvmrxkotlinelahee.preferenes.MySharedPref

class Splash : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash)
        initSplashScreenStatusBar()



        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            if (MySharedPref.getBoolean(PrefKey.IS_LOGGEDIN)) {
                finish()
                startActivity(Intent(this@Splash, HomeActivity::class.java))
                return@Runnable
            } else {
                startActivity(Intent(this@Splash, LoginActivity::class.java))
            }
        }, 2000)
    }
}