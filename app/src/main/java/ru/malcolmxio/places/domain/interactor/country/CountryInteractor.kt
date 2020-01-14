package ru.malcolmxio.places.domain.interactor.country

import ru.malcolmxio.places.di.provider.scheduler.SchedulersProvider
import ru.malcolmxio.places.domain.repository.country.CountryRepository
import javax.inject.Inject

class CountryInteractor @Inject constructor(
    private val api: CountryRepository,
    private val schedulers: SchedulersProvider
) {

    fun getCountries() = api
        .getCountries()
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())

}