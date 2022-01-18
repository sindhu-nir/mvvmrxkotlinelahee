package com.btracsolutions.mvvmrxkotlinelahee.repository

import android.util.Log
import com.btracsolutions.mvvmrxkotlinelahee.networking.RetrofitClient
import io.reactivex.Observable
import java.util.HashMap

object TestKotRepository {


    fun requestGetGeofence( lang: String,  user_api_hash: String  ): Observable<Object> {
        val params = HashMap<String, String>()
        params["lang"] = lang
        params["user_api_hash"] = user_api_hash

        return RetrofitClient.apiService.executeGetGeofenceRequest(
                lang,
                user_api_hash

        )
    }
}