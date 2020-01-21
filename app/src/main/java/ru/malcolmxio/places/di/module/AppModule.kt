package ru.malcolmxio.places.di.module

import android.content.Context
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.layout_container.*
import ru.malcolmxio.places.R
import ru.malcolmxio.places.di.provider.GsonProvider
import ru.malcolmxio.places.di.provider.SharedPreferencesProvider
import ru.malcolmxio.places.di.provider.scheduler.AppSchedulers
import ru.malcolmxio.places.di.provider.scheduler.SchedulersProvider
import ru.malcolmxio.places.ui.RouteActivity
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

}