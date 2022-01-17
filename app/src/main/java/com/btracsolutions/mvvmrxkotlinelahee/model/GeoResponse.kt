package com.banglatrac.carcopolo.kotlin.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GeoResponse(
    @SerializedName("items")
    val items: Items,
    @SerializedName("status")
    val status: Int
)