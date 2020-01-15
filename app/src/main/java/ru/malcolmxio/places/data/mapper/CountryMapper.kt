package ru.malcolmxio.places.data.mapper

import ru.malcolmxio.places.data.model.network.CountryNetworkModel
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
                it.places?.map { placeDto ->
                    Place(
                        0L,
                        placeDto.name ?: "",
                        Coordinates(
                            placeDto.lat?.toLongOrNull() ?: 0L,
                            placeDto.lon?.toLongOrNull() ?: 0L
                        )
                    )
                } ?: listOf()
            )
        } ?: listOf()
    }

}