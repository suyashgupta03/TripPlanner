package com.skyscanner.ui.trip.details;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.skyscanner.ViewModelProviderFactory;
import com.skyscanner.data.DataManager;
import com.skyscanner.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by suyashg on 10/06/18.
 */
@Module
public class TripDetailsModule {

    @Provides
    TripDetailsViewModel tripDetailsViewModel(SchedulerProvider schedulerProvider, DataManager dataManager) {
        return new TripDetailsViewModel(schedulerProvider, dataManager);
    }

    @Provides
    TripDetailsAdapter provideTripDetailsAdapter(TripDetailsFragment fragment) {
        TripDetailsAdapter tripDetailsAdapter = new TripDetailsAdapter(new ArrayList<>());
        tripDetailsAdapter.setListener(fragment);
        return tripDetailsAdapter;
    }

    @Provides
    ViewModelProvider.Factory provideTripDetailsViewModel(TripDetailsViewModel tripDetailsViewModel) {
        return new ViewModelProviderFactory<>(tripDetailsViewModel);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(TripDetailsFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}
