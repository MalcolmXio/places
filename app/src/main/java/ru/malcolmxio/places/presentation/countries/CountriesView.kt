package ru.malcolmxio.places.presentation.countries

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.malcolmxio.places.domain.model.country.Country

@StateStrategyType(AddToEndSingleStrategy::class)
interface CountriesView : MvpView {
    fun showCountries(countries: List<Country>)
    fun showProgress(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToMap(argument: Country)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(msg: String)
}