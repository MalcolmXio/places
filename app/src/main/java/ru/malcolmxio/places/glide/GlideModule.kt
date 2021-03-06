package ru.malcolmxio.places.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.Excludes
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.module.LibraryGlideModule
import okhttp3.OkHttpClient
import ru.malcolmxio.places.App
import java.io.InputStream
import javax.inject.Inject

@Excludes(OkHttpLibraryGlideModule::class)
@GlideModule
class GlideModule : AppGlideModule() {

    @Inject
    lateinit var okHttpClient: OkHttpClient

    init {
        App().appComponent.inject(this)
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val factory = OkHttpUrlLoader.Factory(okHttpClient)

        glide.registry.replace(GlideUrl::class.java, InputStream::class.java, factory)
    }

    override fun isManifestParsingEnabled() = false
}