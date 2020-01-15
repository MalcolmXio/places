package ru.malcolmxio.places.data.model.network

import com.google.gson.annotations.SerializedName

data class CountryNetworkModel(
    @SerializedName("countries")
    val countries: List<CountryNetworkDto>?
)