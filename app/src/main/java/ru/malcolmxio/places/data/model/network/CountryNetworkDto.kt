package ru.malcolmxio.places.data.model.network

import com.google.gson.annotations.SerializedName

data class CountryNetworkDto(
    @SerializedName("country")
    val countryName: String?,
    @SerializedName("bounds")
    val bounds: BoundsDto?,
    @SerializedName("places")
    val places: List<PlaceNetworkDto>?
)