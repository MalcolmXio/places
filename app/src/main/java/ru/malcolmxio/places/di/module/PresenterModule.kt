package ru.malcolmxio.places.di.module

import dagger.Module
import dagger.Provides
import ru.malcolmxio.places.di.scope.FlowScope
import ru.malcolmxio.places.domain.interactor.country.CountryInteractor
import ru.malcolmxio.places.presentation.countries.CountriesPresenter
import ru.malcolmxio.places.presentation.flow.FlowRouter
import ru.malcolmxio.places.presentation.map.MapPresenter

@Module
class PresenterModule {

    @Provides
    @FlowScope
    fun provideCountriesPresenter(interactor: CountryInteractor, router: FlowRouter) =
        CountriesPresenter(interactor, router)

    @Provides
    @FlowScope
    fun provideMapPresenter(router: FlowRouter) = MapPresenter(router)

}