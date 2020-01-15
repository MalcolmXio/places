package ru.malcolmxio.places.data.model.network

import com.google.gson.annotations.SerializedName

data class PlaceNetworkDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lon")
    val lon: String?
)