package com.skyscanner.data.remote;

import com.skyscanner.data.model.api.trip.TripDetailsRequest;
import com.skyscanner.data.model.api.trip.TripDetailsResponse;

import io.reactivex.Single;

/**
 * Created by suyashg on 10/06/18.
 */

public interface ApiHelper {

    Single<TripDetailsResponse> getTripDetails(TripDetailsRequest request);

}
