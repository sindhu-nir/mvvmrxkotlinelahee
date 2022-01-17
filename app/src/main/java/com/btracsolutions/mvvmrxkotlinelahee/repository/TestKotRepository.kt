package com.banglatrac.carcopolo.kotlin.repository

import android.util.Log
import com.banglatrac.carcopolo.kotlin.networking.RetrofitClient
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