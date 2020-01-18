package ru.malcolmxio.places.domain.model.country

import java.io.Serializable

data class Country(
    val id: Long,
    val name: String,
    val bounds: Bounds,
    val places: List<Place>
) : Serializable