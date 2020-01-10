package ru.malcolmxio.places.domain.model.country

data class Place(
    val id: Long,
    val name: String,
    val coordinates: Coordinates
)