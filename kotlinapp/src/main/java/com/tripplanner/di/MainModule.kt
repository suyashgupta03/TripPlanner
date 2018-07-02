package com.tripplanner.di

import com.tripplanner.ui.trip.details.TripDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by suyashg on 21/06/18.
 */
@Module
internal abstract class MainModule {
    @ContributesAndroidInjector
    internal abstract fun contributeTopFragmentInjector(): TripDetailsFragment
}