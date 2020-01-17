package ru.malcolmxio.places.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.malcolmxio.places.di.NavHolder
import ru.malcolmxio.places.di.provider.GsonProvider
import ru.malcolmxio.places.di.provider.SharedPreferencesProvider
import ru.malcolmxio.places.di.provider.scheduler.AppSchedulers
import ru.malcolmxio.places.di.provider.scheduler.SchedulersProvider
import ru.terrakok.cicerone.Cicerone
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    //Global

    @Provides
    @Singleton
    fun provideApplicationContext() = context

    @Provides
    @Singleton
    fun provideGson() = GsonProvider().get()

    @Provides
    @Singleton
    fun provideSharedPreferences() = SharedPreferencesProvider(context).get()

    @Provides
    fun provideSchedulers() = AppSchedulers() as SchedulersProvider

    // Navigation

    private val cicerone = Cicerone.create()

    @Provides
    fun provideRouter() = cicerone.router

    @Provides
    @NavHolder("Global")
    fun provideNavigatorHolder() = cicerone.navigatorHolder

}