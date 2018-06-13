package com.skyscanner.data;

import android.content.Context;

import com.google.gson.Gson;
import com.skyscanner.data.model.api.trip.CompleteTrip;
import com.skyscanner.data.model.api.trip.TripDetailsRequest;
import com.skyscanner.data.model.api.trip.TripDetailsResponse;
import com.skyscanner.data.processing.ApiProcesser;
import com.skyscanner.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by suyashg on 10/06/18.
 */

public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    private final ApiProcesser mApiProcesser;

    private final Context mContext;

    private final Gson mGson;

    @Inject
    public AppDataManager(ApiHelper mApiHelper, Context mContext, Gson mGson, ApiProcesser apiProcesser) {
        this.mApiHelper = mApiHelper;
        this.mApiProcesser = apiProcesser;
        this.mContext = mContext;
        this.mGson = mGson;
    }

    @Override
    public Single<TripDetailsResponse> getTripDetails(TripDetailsRequest request) {
        return mApiHelper.getTripDetails(request);
    }

    @Override
    public List<CompleteTrip> processTripDetailsResponse(TripDetailsResponse response) {
        return mApiProcesser.processTripDetailsResponse(response);
    }
}
