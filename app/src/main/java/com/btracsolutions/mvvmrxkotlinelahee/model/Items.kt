package com.banglatrac.carcopolo.kotlin.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Items(
    @SerializedName("geofences")
    val geofences: List<Geofence>
)