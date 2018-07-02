package com.tripplanner.ui.trip;

import android.databinding.ObservableField;

import com.tripplanner.data.DataManager;
import com.tripplanner.ui.base.BaseViewModel;
import com.tripplanner.ui.trip.details.TripNavigator;
import com.tripplanner.utils.rx.SchedulerProvider;

/**
 * Created by suyashg on 09/06/18.
 */

public class TripViewModel extends BaseViewModel<TripNavigator> {

    public final ObservableField<String> dapartureDate;
    public final ObservableField<String> returnDate;

    public TripViewModel(SchedulerProvider mSchedulerProvider, DataManager mDataManager) {
        super(mSchedulerProvider, mDataManager);
        dapartureDate = new ObservableField<>();
        returnDate = new ObservableField<>();
    }

    public void onDepartureDateClick() {
        getNavigator().showDepartureDateDialog();
    }

    public void onReturnDateClick() {
        getNavigator().showReturnDateDialog();
    }

    public void updateDates(String departure, String returnValue) {
        dapartureDate.set(departure);
        returnDate.set(returnValue);
    }

    public void updateDepartureDate(String departure) {
        dapartureDate.set(departure);
    }

    public void updateReturnDate(String returnValue) {
        returnDate.set(returnValue);
    }
}
