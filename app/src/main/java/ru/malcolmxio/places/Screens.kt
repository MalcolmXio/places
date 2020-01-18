package ru.malcolmxio.places

import ru.malcolmxio.places.domain.model.country.Country
import ru.malcolmxio.places.ui.map.GeoMapFlowFragment
import ru.malcolmxio.places.ui.map.MapFragment
import ru.malcolmxio.places.ui.start.CountriesFragment
import ru.malcolmxio.places.ui.start.MainFlowFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    //Flows
    object StartFlow : SupportAppScreen() {
        override fun getFragment() = MainFlowFragment()
    }

    data class MapFlow(val countryData: Country) : SupportAppScreen() {
        override fun getFragment() = GeoMapFlowFragment.create(countryData)
    }

    //Screens
    //StartFlow
    object Countries : SupportAppScreen() {
        override fun getFragment() = CountriesFragment()
    }

    data class GeoMap(val countryData: Country) : SupportAppScreen() {
        override fun getFragment() = MapFragment.create(countryData)
    }

}