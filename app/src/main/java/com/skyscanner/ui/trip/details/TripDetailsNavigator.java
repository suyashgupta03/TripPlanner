package com.skyscanner.ui.trip.details;

import com.skyscanner.data.model.api.ApiError;
import com.skyscanner.data.model.api.trip.CompleteTrip;

import java.util.List;

/**
 * Created by suyashg on 10/06/18.
 */

public interface TripDetailsNavigator {

    void handleError(ApiError apiError);

    void refreshUI(List<CompleteTrip> completeTrips);

}
