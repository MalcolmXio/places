package ru.malcolmxio.places.data.model.network

import com.google.gson.annotations.SerializedName

data class PointDto(
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lon")
    val lon: String?
) {
    companion object {

        fun empty() = PointDto("0", "0")

    }
}

fun PointDto?.orEmpty() = this ?: PointDto.empty()