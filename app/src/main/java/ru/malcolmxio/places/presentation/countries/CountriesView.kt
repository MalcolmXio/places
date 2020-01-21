package ru.malcolmxio.places.presentation.countries

import androidx.fragment.app.Fragment
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.malcolmxio.places.Screens
import ru.malcolmxio.places.domain.model.country.Country

@StateStrategyType(AddToEndSingleStrategy::class)
interface CountriesView : MvpView {
    fun showCountries(countries: List<Country>)
    fun showProgress(show: Boolean)
    fun navigateTo(screen: Int, args: Country)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(msg: String)
}