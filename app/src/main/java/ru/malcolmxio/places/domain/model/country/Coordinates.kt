package ru.malcolmxio.places.domain.model.country

import java.io.Serializable

data class Coordinates(
    val lat: Double,
    val lon: Double
) : Serializable