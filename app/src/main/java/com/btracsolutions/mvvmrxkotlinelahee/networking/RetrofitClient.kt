package com.btracsolutions.mvvmrxkotlinelahee.networking

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }



    private val myHttpClient: OkHttpClient by lazy {
        OkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(NetworkConnectionInterceptor())
                .retryOnConnectionFailure(true)
//            .addInterceptor(headerAuthorizationInterceptor)
                .build()
    }


    private val gson: Gson by lazy {
        GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create()
    }

    // This is the fallback URL for this application
    private var baseURL = "https://www.purebasic.com.bd/api/"

    private var retrofit: Retrofit = getNewInstance(baseURL)

    private fun getNewInstance(url: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(url)
                .client(myHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun updateBaseURL(newURL: String) {
        this.baseURL = newURL
        retrofit = getNewInstance(newURL)
    }

    val getRetrofitInstance: Retrofit
        get() = retrofit

    val apiService: ApiService
        get() = getRetrofitInstance.create(ApiService::class.java)


}