package com.tripplanner.ui.trip;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import com.tripplanner.BR;
import com.tripplanner.R;
import com.tripplanner.data.model.api.trip.TripDetailsRequest;
import com.tripplanner.databinding.ActivityTripBinding;
import com.tripplanner.ui.base.BaseActivity;
import com.tripplanner.ui.trip.details.TripDetailsFragment;
import com.tripplanner.ui.trip.details.TripNavigator;
import com.tripplanner.utils.CommonUtils;

import java.util.Calendar;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by suyashg on 09/06/18.
 */

public class TripActivity extends BaseActivity<ActivityTripBinding, TripViewModel> implements HasSupportFragmentInjector,
        TripNavigator, DatePickerDialog.OnDateSetListener {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    TripViewModel mTripViewModel;
    private ActivityTripBinding mActivityTripBinding;
    @Inject
    TripDetailsRequest tripDetailsRequest;
    @Inject
    DatePickerDialog datePickerDialog;

    @Override
    public TripViewModel getViewModel() {
        return mTripViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trip;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTripBinding = getViewDataBinding();
        mTripViewModel.setNavigator(this);
        setup();
    }

    private void setup() {
        setSupportActionBar(mActivityTripBinding.toolbar);
        String departure = getString(R.string.label_depart_with_colon) + CommonUtils.prepareCurrentDate(CommonUtils.getCurrentLocale(this));
        String returnValue = getString(R.string.label_return_with_colon) + CommonUtils.prepareCurrentDate(CommonUtils.getCurrentLocale(this));
        mTripViewModel.updateDates(departure, returnValue);
        TripDetailsFragment tripDetailsFragment = TripDetailsFragment.newInstance(tripDetailsRequest);
        getSupportFragmentManager().beginTransaction()
                .replace(mActivityTripBinding.flTripDetailsContainer.getId(),
                        tripDetailsFragment, TripDetailsFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void showDepartureDateDialog() {
        datePickerDialog.getDatePicker().setTag(this.getString(R.string.label_depart));
        datePickerDialog.show();

    }

    @Override
    public void showReturnDateDialog() {
        datePickerDialog.getDatePicker().setTag(this.getString(R.string.label_return));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        TripDetailsFragment fragmentByTag = (TripDetailsFragment) getSupportFragmentManager().findFragmentByTag(TripDetailsFragment.class.getSimpleName());
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        if (view.getTag().equals(this.getString(R.string.label_depart))) {
            String departure = getString(R.string.label_depart_with_colon) +
                    CommonUtils.prepareDateFromValues(CommonUtils.getCurrentLocale(this), year, month, dayOfMonth);
            mTripViewModel.updateDepartureDate(departure);
            tripDetailsRequest.setDepartureDate(calendar.getTimeInMillis());
        } else if(view.getTag().equals(this.getString(R.string.label_return))) {
            String returnValue = getString(R.string.label_return_with_colon) +
                    CommonUtils.prepareDateFromValues(CommonUtils.getCurrentLocale(this), year, month, dayOfMonth);
            mTripViewModel.updateReturnDate(returnValue);
            tripDetailsRequest.setReturnDate(calendar.getTimeInMillis());
        }
        fragmentByTag.refresh(tripDetailsRequest);
    }

    public void updateDates(long daparture, long returnValue) {
        Calendar departCal = Calendar.getInstance();
        Calendar returnCal = Calendar.getInstance();
        departCal.setTimeInMillis(daparture);
        returnCal.setTimeInMillis(returnValue);
        String departureText = getString(R.string.label_depart_with_colon) +
                CommonUtils.prepareDateFromValues(CommonUtils.getCurrentLocale(this), departCal);
        String returnText = getString(R.string.label_return_with_colon) +
                CommonUtils.prepareDateFromValues(CommonUtils.getCurrentLocale(this), returnCal);
        mTripViewModel.updateDates(departureText, returnText);
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getTripListIdlingResource() {
        TripDetailsFragment fragmentByTag = (TripDetailsFragment) getSupportFragmentManager().findFragmentByTag(TripDetailsFragment.class.getSimpleName());
        return fragmentByTag.getTripListIdlingResource();
    }
}
