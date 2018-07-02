package com.tripplanner.ui.trip.details;

import com.tripplanner.data.DataManager;
import com.tripplanner.data.model.api.trip.TripDetailsRequest;
import com.tripplanner.data.model.api.trip.TripDetailsResponse;
import com.tripplanner.utils.rx.TestSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static com.tripplanner.utils.AppConstants.DESTINATION_AIRPORT_CODE;
import static com.tripplanner.utils.AppConstants.NUMBER_OF_TICKETS;
import static com.tripplanner.utils.AppConstants.ORIGIN_AIRPORT_CODE;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by suyashg on 13/06/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class TripDetailsViewModelTest {

    @Mock
    TripDetailsNavigator mTripNavigator;
    @Mock
    DataManager mMockDataManager;

    private TripDetailsViewModel mTripDetailsViewModel;
    private TestScheduler mTestScheduler;

    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mTripDetailsViewModel = new TripDetailsViewModel(testSchedulerProvider ,mMockDataManager);
        mTripDetailsViewModel.setNavigator(mTripNavigator);
    }

    @After
    public void tearDown() throws Exception {
        mTestScheduler = null;
        mTripDetailsViewModel = null;
        mTripNavigator = null;
    }

    @Test
    public void testServerTripDetailsSuccess() {
        TripDetailsResponse detailsResponse = new TripDetailsResponse();
        TripDetailsRequest tripDetailsRequest = new TripDetailsRequest(ORIGIN_AIRPORT_CODE, DESTINATION_AIRPORT_CODE,
                Calendar.getInstance().getTimeInMillis(), Calendar.getInstance().getTimeInMillis(), NUMBER_OF_TICKETS);
        doReturn(Single.just(detailsResponse))
                .when(mMockDataManager)
                .getTripDetails(tripDetailsRequest);

        mTripDetailsViewModel.fetchTripDetails(tripDetailsRequest);
        mTestScheduler.triggerActions();

        verify(mTripNavigator).refreshUI(new ArrayList<>());
    }

}
