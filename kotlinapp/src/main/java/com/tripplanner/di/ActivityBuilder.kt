package com.tripplanner.di

import com.tripplanner.ui.trip.TripActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by suyashg on 21/06/18.
 */
@Module
internal abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainInjector(): TripActivity
}