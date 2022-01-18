package com.btracsolutions.mvvmrxkotlinelahee.repository

import com.btracsolutions.mvvmrxkotlinelahee.networking.RetrofitClient
import io.reactivex.Observable
import java.util.HashMap

object AuthRepository {

    fun requestLogin( email: String,  password: String  ): Observable<Object> {
        val params = HashMap<String, String>()
        params["email"] = email
        params["password"] = password

        return RetrofitClient.apiService.executeLoginRequest(
            email,
            password

        )
    }
}