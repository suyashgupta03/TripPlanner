package com.tripplanner.di

import dagger.Module

/**
 * Created by suyashg on 21/06/18.
 */
@Module(includes = [NetworkModule::class, ViewModelModule::class])
object AppModule {
    //If required
}