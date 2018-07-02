package com.tripplanner.di

import com.tripplanner.BaseApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by suyashg on 21/06/18.
 */
@Singleton
@Component(
        modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class
        ])
interface AppComponent : AndroidInjector<BaseApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApp): Builder

        fun network(network: NetworkModule): Builder

        fun build(): AppComponent
    }

    override fun inject(app: BaseApp)

}