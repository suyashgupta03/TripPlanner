package com.skyscanner.ui.trip.details;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by suyashg on 10/06/18.
 */
@Module
public abstract class TripDetailsProvider {

    @ContributesAndroidInjector(modules = TripDetailsModule.class)
    abstract TripDetailsFragment provideTripDetailsFragmentFactory();
}
