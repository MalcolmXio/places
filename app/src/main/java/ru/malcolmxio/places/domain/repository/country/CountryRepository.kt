package ru.malcolmxio.places.domain.repository.country

import io.reactivex.Single
import ru.malcolmxio.places.domain.model.country.Country

interface CountryRepository {

    fun getCountries(): Single<List<Country>>

}