package com.tripplanner.di

import com.squareup.moshi.Moshi
import com.tripplanner.BaseApp
import com.tripplanner.utils.ApplicationJsonAdapterFactory
import com.tripplanner.utils.InstantAdapter
import com.tripplanner.utils.Memory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by suyashg on 21/06/18.
 */
@Module
class NetworkModule {

    open fun buildOkHttpClient(app: BaseApp): OkHttpClient =
            OkHttpClient.Builder()
                    .connectTimeout(10L, TimeUnit.SECONDS)
                    .writeTimeout(10L, TimeUnit.SECONDS)
                    .readTimeout(30L, TimeUnit.SECONDS)
                    .cache(Cache(File(app.cacheDir, "OkCache"),
                            Memory.calcCacheSize(app, .25f)))
                    .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(app: BaseApp): OkHttpClient = buildOkHttpClient(app)

    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder()
            .add(ApplicationJsonAdapterFactory.INSTANCE)
            .add(InstantAdapter.INSTANCE)
            .build()
}