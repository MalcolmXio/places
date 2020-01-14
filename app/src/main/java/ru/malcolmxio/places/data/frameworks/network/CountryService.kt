package ru.malcolmxio.places.data.frameworks.network

import io.reactivex.Single
import retrofit2.http.GET
import ru.malcolmxio.places.domain.model.country.Country

interface CountryService {

    @GET(ARG_COUNTRIES_FILE_NAME)
    fun getCountries(): Single<List<Country>>

    companion object {
        private const val ARG_COUNTRIES_FILE_NAME = "places.json"
    }

}