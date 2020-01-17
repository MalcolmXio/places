package ru.malcolmxio.places.presentation.map

import moxy.InjectViewState
import ru.malcolmxio.places.presentation.base.BasePresenter
import ru.malcolmxio.places.presentation.flow.FlowRouter
import javax.inject.Inject

@InjectViewState
class MapPresenter @Inject constructor(private val router: FlowRouter) : BasePresenter<MapView>() {

    fun onBackPressed() = router.exit()

}