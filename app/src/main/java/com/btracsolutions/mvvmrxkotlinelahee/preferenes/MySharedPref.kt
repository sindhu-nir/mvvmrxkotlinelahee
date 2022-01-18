package com.btracsolutions.mvvmrxkotlinelahee.preferenes

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.btracsolutions.mvvmrxkotlinelahee.preference.PrefKey

object MySharedPref {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    fun initAppPreference(application: Application) {
        sharedPreferences =
            application.getSharedPreferences(PrefKey.APP_PREFERENCE, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun setBoolean(key: String?, value: Boolean) {
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun getBoolean(key: String?): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun setString(key: String?, value: String?) {
            editor.putString(key, value)
            editor.commit()

    }
    fun getString(key: String?):String? {
        var value = sharedPreferences.getString(key, "")
        return  value


    }

    fun getUserApiHash(): String? {
        return sharedPreferences!!.getString( PrefKey.USER_API_HASH, "0")
    }

    fun setUserApiHash(userApiHash: String?) {
        editor = sharedPreferences!!.edit()
        editor?.putString(PrefKey.USER_API_HASH, userApiHash)
        editor?.commit()
    }

    fun getUserid(): Int {
        return sharedPreferences!!.getInt(PrefKey.USER_ID, 0)
    }

    fun setUserid(Userid: Int) {
        editor = sharedPreferences!!.edit()
        editor?.putInt(PrefKey.USER_ID, Userid)
        editor?.commit()
    }

    fun getUserEmail(): String? {
        return sharedPreferences!!.getString(PrefKey.USER_EMAIL, "0")
    }

    fun setUsserEmail(userEmail: String?) {
        editor = sharedPreferences!!.edit()
        editor?.putString(PrefKey.USER_EMAIL, userEmail)
        editor?.commit()
    }

    fun getDeviceId(): String? {
        return sharedPreferences!!.getString(PrefKey.DEVICE_ID, "0")
    }

    fun setDeviceId(id: String?) {
        editor = sharedPreferences!!.edit()
        editor?.putString(PrefKey.DEVICE_ID, id)
        editor?.commit()
    }


    fun getDriverLicenceDate(reuestcode: String?): Long {
        return sharedPreferences!!.getLong(reuestcode, 0)
    }

    fun setDriverLicenceDate(reuestcode: String?, data: Long) {
        editor = sharedPreferences!!.edit()
        editor?.putLong(reuestcode, data)
        editor?.commit()
    }

    fun getAccessToken(): String? {
        return sharedPreferences!!.getString(PrefKey.ACCESS_TOKEN, "0")
    }

    fun setAccessToken(userApiHash: String?) {
        editor = sharedPreferences!!.edit()
        editor?.putString(PrefKey.ACCESS_TOKEN, userApiHash)
        editor?.commit()
    }


    fun clearAll() {
        editor = sharedPreferences!!.edit()
        editor?.clear()
        editor?.commit()
    }
}