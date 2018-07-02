package com.tripplanner

import android.content.Context
import com.tripplanner.di.DaggerAppComponent
import com.tripplanner.di.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by suyashg on 21/06/18.
 */
open class BaseApp : DaggerApplication() {

    lateinit var androidInjector: AndroidInjector<out DaggerApplication>

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        androidInjector = DaggerAppComponent.builder()
                .application(this)
                .network(networkModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        //initTimber()
        //initThreeTenABP()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    protected open fun networkModule(): NetworkModule = NetworkModule()
}