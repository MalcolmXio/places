package ru.malcolmxio.places

import android.app.Application
import ru.malcolmxio.places.di.component.AppComponent
import ru.malcolmxio.places.di.component.DaggerAppComponent
import ru.malcolmxio.places.di.component.FlowComponent
import ru.malcolmxio.places.di.module.AppModule
import ru.malcolmxio.places.di.module.FlowNavigationModule
import ru.terrakok.cicerone.Router

class App : Application() {

    lateinit var appComponent: AppComponent
    var flowComponent: FlowComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    fun createFlowComponent(router: Router) {
        if (flowComponent == null) {
            flowComponent = appComponent.plusFlowComponent(FlowNavigationModule(router))
        }
    }

    fun destroyFlowComponent() {
        flowComponent = null
    }

}