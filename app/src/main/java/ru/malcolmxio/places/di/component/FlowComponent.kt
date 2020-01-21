package ru.malcolmxio.places.di.component

import dagger.Subcomponent
import ru.malcolmxio.places.di.module.PresenterModule
import ru.malcolmxio.places.di.scope.FlowScope
import ru.malcolmxio.places.ui.map.MapFragment
import ru.malcolmxio.places.ui.start.CountriesFragment

@FlowScope
@Subcomponent(modules = [PresenterModule::class])
interface FlowComponent {

    fun inject(countriesFragment: CountriesFragment)

    fun inject(mapFragment: MapFragment)

}