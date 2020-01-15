package ru.malcolmxio.places.data.model.network

import com.google.gson.annotations.SerializedName

data class CountryNetworkDto(
    @SerializedName("country")
    val countryName: String?,
    @SerializedName("places")
    val places: List<PlaceNetworkDto>?
)