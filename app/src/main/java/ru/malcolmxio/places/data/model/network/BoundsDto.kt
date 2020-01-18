package ru.malcolmxio.places.data.model.network

import com.google.gson.annotations.SerializedName

data class BoundsDto(
    @SerializedName("bottomLeftPoint")
    val bottomLeftPoint: PointDto?,
    @SerializedName("upperRightPoint")
    val upperRightPoint: PointDto?
)

fun BoundsDto?.orEmpty() = this ?: BoundsDto(PointDto.empty(), PointDto.empty())