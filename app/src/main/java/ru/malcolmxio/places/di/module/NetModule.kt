package ru.malcolmxio.places.di.module

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.malcolmxio.places.data.frameworks.network.CountryService
import ru.malcolmxio.places.data.repository.country.CountryRepositoryImpl
import ru.malcolmxio.places.di.provider.CountryServiceProvider
import ru.malcolmxio.places.di.provider.OkHttpClientProvider
import ru.malcolmxio.places.domain.repository.country.CountryRepository
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context) =
        OkHttpClientProvider(context).get()

    @Provides
    @Singleton
    fun provideCountryRepository(service: CountryService) =
        CountryRepositoryImpl(service) as CountryRepository

    @Provides
    @Singleton
    fun provideCountryService(gson: Gson, okHttpClient: OkHttpClient) =
        CountryServiceProvider(gson, okHttpClient, ARG_SERVER_PATH).get()

    companion object {
        private const val ARG_SERVER_PATH = "https://github.com/MalcolmXio/placesApi/blob/master/"
    }

}