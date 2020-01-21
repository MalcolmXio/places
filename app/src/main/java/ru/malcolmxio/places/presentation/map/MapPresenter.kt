package ru.malcolmxio.places.presentation.map

import moxy.InjectViewState
import ru.malcolmxio.places.presentation.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class MapPresenter @Inject constructor() : BasePresenter<MapView>() {

    fun onBackPressed() {
        //Toast()controller.exit()
    }

}