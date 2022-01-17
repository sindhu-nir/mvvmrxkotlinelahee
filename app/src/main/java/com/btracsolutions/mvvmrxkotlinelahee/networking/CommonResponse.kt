package com.banglatrac.carcopolo.kotlin.networking

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CommonResponse(
        @SerializedName("error_code")
        val error_code: Int,
        @SerializedName("message")
        val message: String,
        @SerializedName("success")
        val success: Boolean
)
