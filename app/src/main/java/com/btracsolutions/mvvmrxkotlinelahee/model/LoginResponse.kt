package com.btracsolutions.mvvmrxkotlinelahee.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class LoginResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("next")
    val next: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("success")
    val success: Boolean
)

@Keep
data class Data(
    @SerializedName("opt")
    val opt: Int,
    @SerializedName("user_id")
    val userId: Int
)