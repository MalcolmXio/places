package ru.malcolmxio.places.data.mapper

import ru.malcolmxio.places.data.model.network.CountryNetworkModel
import ru.malcolmxio.places.data.model.network.orEmpty
import ru.malcolmxio.places.domain.model.country.Bounds
import ru.malcolmxio.places.domain.model.country.Coordinates
import ru.malcolmxio.places.domain.model.country.Country
import ru.malcolmxio.places.domain.model.country.Place
import javax.inject.Inject

class CountryMapper @Inject constructor() : Mapper<CountryNetworkModel, List<Country>> {

    override fun map(input: CountryNetworkModel): List<Country> {
        return input.countries?.map {
            Country(
                0L,
                it.countryName ?: "",
                Bounds(
                    Coordinates(
                        it.bounds.orEmpty().bottomLeftPoint.orEmpty().lat?.toDoubleOrNull() ?: 0.0,
                        it.bounds.orEmpty().bottomLeftPoint.orEmpty().lon?.toDoubleOrNull() ?: 0.0
                    ),
                    Coordinates(
                        it.bounds.orEmpty().upperRightPoint.orEmpty().lat?.toDoubleOrNull() ?: 0.0,
                        it.bounds.orEmpty().upperRightPoint.orEmpty().lon?.toDoubleOrNull() ?: 0.0
                    )
                ),
                it.places?.map { placeDto ->
                    Place(
                        0L,
                        placeDto.name ?: "",
                        Coordinates(
                            placeDto.lat?.toDoubleOrNull() ?: 0.0,
                            placeDto.lon?.toDoubleOrNull() ?: 0.0
                        )
                    )
                } ?: listOf()
            )
        } ?: listOf()
    }

}