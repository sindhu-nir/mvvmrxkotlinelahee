package com.banglatrac.carcopolo.kotlin.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Geofence(
    @SerializedName("active")
    val active: Int,
    @SerializedName("center")
    val center: Any,
    @SerializedName("coordinates")
    val coordinates: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("device_id")
    val deviceId: Any,
    @SerializedName("group_id")
    val groupId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("polygon_color")
    val polygonColor: String,
    @SerializedName("radius")
    val radius: Any,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int
)