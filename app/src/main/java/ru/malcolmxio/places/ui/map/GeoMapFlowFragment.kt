package ru.malcolmxio.places.ui.map

import android.os.Bundle
import ru.malcolmxio.places.Screens
import ru.malcolmxio.places.domain.model.country.Country
import ru.malcolmxio.places.ui.base.FlowFragment
import ru.malcolmxio.places.util.argument

class GeoMapFlowFragment : FlowFragment() {

    private val countryData: Country by argument(ARG_COUNTRY_DATA)

    override fun getLaunchScreen() = Screens.GeoMap(countryData)

    companion object {

        private const val ARG_COUNTRY_DATA = "ARG_COUNTRY_DATA"

        fun create(countryData: Country) =
            GeoMapFlowFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_COUNTRY_DATA, countryData)
                }
            }

    }

}