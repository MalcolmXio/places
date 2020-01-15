package ru.malcolmxio.places.data.frameworks.network

import io.reactivex.Single
import retrofit2.http.GET
import ru.malcolmxio.places.data.model.network.CountryNetworkModel

interface CountryService {

    @GET(ARG_COUNTRIES_FILE_NAME)
    fun getCountries(): Single<CountryNetworkModel>

    companion object {
        private const val ARG_COUNTRIES_FILE_NAME = "bins/q2d26"
    }

}