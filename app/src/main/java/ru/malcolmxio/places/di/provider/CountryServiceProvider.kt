package ru.malcolmxio.places.di.provider

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.malcolmxio.places.data.frameworks.network.CountryService
import javax.inject.Inject
import javax.inject.Provider

class CountryServiceProvider @Inject constructor(
    private val gson: Gson,
    private val okHttpClient: OkHttpClient,
    private val serverPath: String
) : Provider<CountryService> {

    override fun get() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .baseUrl(serverPath)
        .build()
        .create(CountryService::class.java)

}