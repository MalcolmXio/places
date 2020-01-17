package ru.malcolmxio.places.di.component

import dagger.Subcomponent
import ru.malcolmxio.places.di.module.FlowNavigationModule
import ru.malcolmxio.places.di.module.PresenterModule
import ru.malcolmxio.places.di.scope.FlowScope
import ru.malcolmxio.places.ui.base.FlowFragment
import ru.malcolmxio.places.ui.map.MapFragment
import ru.malcolmxio.places.ui.start.CountriesFragment

@FlowScope
@Subcomponent(modules = [FlowNavigationModule::class, PresenterModule::class])
interface FlowComponent {

    fun inject(flowFragment: FlowFragment)

    fun inject(countriesFragment: CountriesFragment)

    fun inject(mapFragment: MapFragment)

}