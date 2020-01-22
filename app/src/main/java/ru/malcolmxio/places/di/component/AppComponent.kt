package ru.malcolmxio.places.di.component

import com.bumptech.glide.module.AppGlideModule
import dagger.Component
import ru.malcolmxio.places.di.module.AppModule
import ru.malcolmxio.places.di.module.NetModule
import ru.malcolmxio.places.di.module.PresenterModule
import ru.malcolmxio.places.ui.RouteActivity
import ru.malcolmxio.places.ui.base.BaseFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface AppComponent {

    fun plusFlowComponent(module: PresenterModule): FlowComponent

    fun inject(activity: RouteActivity)

    fun inject(fragment: BaseFragment)

    fun inject(glideModule: AppGlideModule)

}