package ru.malcolmxio.places.domain.model.country

import java.io.Serializable

data class Place(
    val id: Long,
    val name: String,
    val coordinates: Coordinates
) : Serializable