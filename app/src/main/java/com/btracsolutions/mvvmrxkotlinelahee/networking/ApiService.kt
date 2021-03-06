package com.btracsolutions.mvvmrxkotlinelahee.networking


import com.btracsolutions.mvvmrxkotlinelahee.utils.ApiConstants
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET( "get_geofences")
    fun executeGetGeofenceRequest(
            @Query("lang") lang: String,
            @Query("user_api_hash") userApiHash: String


    ): Observable<Object>

    @POST(ApiConstants.LOGIN)
    fun executeLoginRequest(
            @Query("email") lang: String,
            @Query("password") userApiHash: String

    ): Observable<Object>



}

