package ru.malcolmxio.places.data.repository.country

import io.reactivex.Single
import ru.malcolmxio.places.data.frameworks.network.CountryService
import ru.malcolmxio.places.domain.model.country.Country
import ru.malcolmxio.places.domain.repository.country.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val service: CountryService) : CountryRepository {

    override fun getCountries(): Single<List<Country>> {
        return service.getCountries()
    }

}