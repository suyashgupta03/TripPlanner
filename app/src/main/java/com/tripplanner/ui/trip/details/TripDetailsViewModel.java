package com.tripplanner.ui.trip.details;

import android.support.test.espresso.IdlingResource;

import com.tripplanner.data.DataManager;
import com.tripplanner.data.model.api.ApiError;
import com.tripplanner.data.model.api.trip.CompleteTrip;
import com.tripplanner.data.model.api.trip.TripDetailsRequest;
import com.tripplanner.data.model.api.trip.TripDetailsResponse;
import com.tripplanner.ui.base.BaseViewModel;
import com.tripplanner.utils.AppLogger;
import com.tripplanner.utils.ParsingUtils;
import com.tripplanner.utils.SimpleIdlingResource;
import com.tripplanner.utils.rx.SchedulerProvider;

import java.util.List;

import static com.tripplanner.utils.AppConstants.ERROR_DEFAULT_CODE;
import static com.tripplanner.utils.AppConstants.ERROR_DEFAULT_MSG;
import static com.tripplanner.utils.AppConstants.ERROR_DEFAULT_STATUS;

/**
 * Created by suyashg on 10/06/18.
 */

public class TripDetailsViewModel extends BaseViewModel<TripDetailsNavigator> {

    private SimpleIdlingResource newsListIdlingResource;

    public TripDetailsViewModel(SchedulerProvider mSchedulerProvider, DataManager mDataManager) {
        super(mSchedulerProvider, mDataManager);
        newsListIdlingResource = new SimpleIdlingResource();
        newsListIdlingResource.setIdleState(false);
    }

    public void fetchTripDetails(TripDetailsRequest tripDetailsRequest) {
        setIsLoading(true);
        newsListIdlingResource.setIdleState(false);
        getCompositeDisposable().add(getDataManager()
                .getTripDetails(tripDetailsRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    AppLogger.d("Success:"+response.toString());
                    if (response != null) {
                        this.processTripDetailsData(response);
                    } else {
                        ApiError apiError = new ApiError(ERROR_DEFAULT_CODE, ERROR_DEFAULT_STATUS, ERROR_DEFAULT_MSG);
                        getNavigator().handleError(apiError);
                    }
                    setIsLoading(false);
                    newsListIdlingResource.setIdleState(true);
                }, throwable -> {
                    ApiError apiError = ParsingUtils.parseError(throwable);
                    setIsLoading(false);
                    AppLogger.e(throwable.getMessage(), throwable);
                    getNavigator().handleError(apiError);
                    newsListIdlingResource.setIdleState(true);
                }));
    }

    private void processTripDetailsData(TripDetailsResponse response) {
        AppLogger.d("processTripDetailsData");
        List<CompleteTrip> tripItems = getDataManager().processTripDetailsResponse(response);
        getNavigator().refreshUI(tripItems);
    }

    public IdlingResource getTripListIdlingResource() {
        return newsListIdlingResource;
    }
}
