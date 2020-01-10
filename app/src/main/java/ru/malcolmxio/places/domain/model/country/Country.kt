package ru.malcolmxio.places.domain.model.country

data class Country(
    val id: Long,
    val name: String,
    val places: List<Place>
)