package ru.malcolmxio.places.domain.model.country

import java.io.Serializable

data class Bounds(
    val bottomLeftPoint: Coordinates,
    val upperRightPoint: Coordinates
) : Serializable