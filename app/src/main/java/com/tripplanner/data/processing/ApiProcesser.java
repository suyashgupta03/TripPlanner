package com.tripplanner.data.processing;

import com.tripplanner.data.model.api.trip.CompleteTrip;
import com.tripplanner.data.model.api.trip.TripDetailsResponse;

import java.util.List;

/**
 * Created by suyashg on 11/06/18.
 */

public interface ApiProcesser {

    List<CompleteTrip> processTripDetailsResponse(TripDetailsResponse response);
}
