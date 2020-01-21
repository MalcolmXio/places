package ru.malcolmxio.places

import androidx.multidex.MultiDexApplication
import ru.malcolmxio.places.di.component.AppComponent
import ru.malcolmxio.places.di.component.DaggerAppComponent
import ru.malcolmxio.places.di.component.FlowComponent
import ru.malcolmxio.places.di.module.AppModule
import ru.malcolmxio.places.di.module.PresenterModule

class App : MultiDexApplication() {

    lateinit var appComponent: AppComponent
    val components = hashMapOf<String, FlowComponent>()

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    fun addFlowComponent(scopeName: String) {
        components[scopeName] = appComponent.plusFlowComponent(PresenterModule())
    }

    fun destroyFlowComponent(scopeName: String?) {
        components.remove(scopeName)
    }

}