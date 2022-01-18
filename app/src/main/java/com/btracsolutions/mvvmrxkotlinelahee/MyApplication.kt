package com.btracsolutions.mvvmrxkotlinelahee
import android.app.Application
import android.content.Context
import com.banglatrac.carcopolo.kotlin.utils.NetworkConnectivityChecker
import com.btracsolutions.mvvmrxkotlinelahee.preferenes.MySharedPref.initAppPreference

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
     //   RemoteConfigUtils.init()
        initAppPreference(this)
        NetworkConnectivityChecker.initConnectionManager(this)
    }



}