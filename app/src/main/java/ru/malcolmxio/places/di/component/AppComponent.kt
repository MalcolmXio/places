package ru.malcolmxio.places.di.component

import dagger.Component
import ru.malcolmxio.places.di.module.AppModule
import ru.malcolmxio.places.di.module.FlowNavigationModule
import ru.malcolmxio.places.di.module.NetModule
import ru.malcolmxio.places.ui.RouteActivity
import ru.malcolmxio.places.ui.base.BaseFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface AppComponent {

    fun plusFlowComponent(module: FlowNavigationModule): FlowComponent

    fun inject(activity: RouteActivity)

    fun inject(fragment: BaseFragment)

}