package ru.malcolmxio.places

import ru.malcolmxio.places.domain.model.country.Place
import ru.malcolmxio.places.ui.map.MapFragment
import ru.malcolmxio.places.ui.start.CountriesFragment
import ru.malcolmxio.places.ui.start.MainFlowFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    //Flows
    object StartFlow : SupportAppScreen() {
        override fun getFragment() = MainFlowFragment()
    }

    //Screens
    //StartFlow
    object Countries : SupportAppScreen() {
        override fun getFragment() = CountriesFragment()
    }

    data class GeoMap(val geoPointData: ArrayList<Place>) : SupportAppScreen() {
        override fun getFragment() = MapFragment.create(geoPointData)
    }

}