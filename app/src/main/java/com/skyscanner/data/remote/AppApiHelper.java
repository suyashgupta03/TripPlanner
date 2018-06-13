package com.skyscanner.data.remote;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.skyscanner.data.model.api.trip.TripDetailsRequest;
import com.skyscanner.data.model.api.trip.TripDetailsResponse;
import com.skyscanner.utils.AppConstants;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by suyashg on 10/06/18.
 */

public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {
    }

    @Override
    public Single<TripDetailsResponse> getTripDetails(TripDetailsRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_TRIP_SEARCH)
                .addHeaders(AppConstants.PARAM_CONTENT_TYPE, AppConstants.PARAM_VALUE_CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle(TripDetailsResponse.class);
    }
}
