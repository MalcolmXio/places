package ru.malcolmxio.places.di.module

import dagger.Module
import dagger.Provides
import ru.malcolmxio.places.di.NavHolder
import ru.malcolmxio.places.di.scope.FlowScope
import ru.malcolmxio.places.presentation.flow.FlowRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@Module
class FlowNavigationModule(router: Router) {

    private val cicerone = Cicerone.create(FlowRouter(router))

    @Provides
    @FlowScope
    fun provideFlowRouter() = cicerone.router

    @Provides
    @FlowScope
    @NavHolder("Fragment")
    fun provideNavigatorHolder() = cicerone.navigatorHolder

}