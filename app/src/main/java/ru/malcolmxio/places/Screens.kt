package ru.malcolmxio.places

import ru.malcolmxio.places.ui.start.CountriesFragment
import ru.malcolmxio.places.ui.start.MainFlowFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    //Flows
    object StartFlow : SupportAppScreen() {
        override fun getFragment() = MainFlowFragment()
    }

    //Screens
    object Countries : SupportAppScreen() {
        override fun getFragment() = CountriesFragment()
    }

}