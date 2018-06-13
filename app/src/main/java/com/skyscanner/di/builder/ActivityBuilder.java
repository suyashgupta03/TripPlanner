package com.skyscanner.di.builder;

import com.skyscanner.ui.trip.TripActivity;
import com.skyscanner.ui.trip.TripActivityModule;
import com.skyscanner.ui.trip.details.TripDetailsProvider;

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
