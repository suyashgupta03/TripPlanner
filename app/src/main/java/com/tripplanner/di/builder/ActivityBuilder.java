package com.tripplanner.di.builder;

import com.tripplanner.ui.trip.TripActivity;
import com.tripplanner.ui.trip.TripActivityModule;
import com.tripplanner.ui.trip.details.TripDetailsProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by suyashg on 09/06/18.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            TripActivityModule.class,
            TripDetailsProvider.class})
    abstract TripActivity bindTripActivity();

}
