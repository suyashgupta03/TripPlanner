package com.skyscanner.ui.trip.details;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.skyscanner.BR;
import com.skyscanner.R;
import com.skyscanner.data.model.api.ApiError;
import com.skyscanner.data.model.api.trip.CompleteTrip;
import com.skyscanner.data.model.api.trip.TripDetailsRequest;
import com.skyscanner.databinding.FragmentTripDetailsBinding;
import com.skyscanner.ui.base.BaseFragment;
import com.skyscanner.ui.trip.TripActivity;
import com.skyscanner.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by suyashg on 09/06/18.
 */

public class TripDetailsFragment extends BaseFragment<FragmentTripDetailsBinding, TripDetailsViewModel> implements
        TripDetailsNavigator, TripDetailsAdapter.TripAdapterListener {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    TripDetailsAdapter mTripDetailsAdapter;

    private TripDetailsViewModel mTipDetailsViewModel;
    private TripDetailsRequest mTripDetailsRequest;
    FragmentTripDetailsBinding mFragmentBinding;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_trip_details;
    }

    @Override
    public TripDetailsViewModel getViewModel() {
        mTipDetailsViewModel = ViewModelProviders.of(this, mViewModelFactory).get(TripDetailsViewModel.class);
        return mTipDetailsViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTipDetailsViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentBinding = getViewDataBinding();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTripDetailsRequest = getArguments().getParcelable(AppConstants.PARAM_TRIP_DATA);
        mTipDetailsViewModel.fetchTripDetails(mTripDetailsRequest);
    }

    public void refresh(TripDetailsRequest tripDetailsRequest) {
        this.mTripDetailsRequest = tripDetailsRequest;
        mTipDetailsViewModel.fetchTripDetails(tripDetailsRequest);
    }

    public static TripDetailsFragment newInstance(TripDetailsRequest tripDetailsRequest) {
        Bundle args = new Bundle();
        args.putParcelable(AppConstants.PARAM_TRIP_DATA, tripDetailsRequest);
        TripDetailsFragment fragment = new TripDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void handleError(ApiError apiError) {
        if (mFragmentBinding.rlTripDetails.getAdapter() == null) {
            mFragmentBinding.rlTripDetails.setLayoutManager(mLayoutManager);
            mFragmentBinding.rlTripDetails.setAdapter(mTripDetailsAdapter);
        }
        if(this.isNetworkConnected()) {
            mTripDetailsAdapter.clearItemsForError(apiError.getMessage());
        } else {
            mTripDetailsAdapter.clearItemsForError(getString(R.string.error_no_internet));
        }
    }

    @Override
    public void refreshUI(List<CompleteTrip> completeTrips) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentBinding.rlTripDetails.setLayoutManager(mLayoutManager);
        mFragmentBinding.rlTripDetails.setItemAnimator(new DefaultItemAnimator());
        mTripDetailsAdapter.addItems(completeTrips);
        mFragmentBinding.rlTripDetails.setAdapter(mTripDetailsAdapter);
        if (getActivity() instanceof TripActivity) {
            TripActivity activity = (TripActivity) getActivity();
            activity.updateDates(mTripDetailsRequest.getDepartureDate(), mTripDetailsRequest.getReturnDate());
        }
    }

    @Override
    public void onRetryClick() {
        mTipDetailsViewModel.fetchTripDetails(mTripDetailsRequest);
    }

    public IdlingResource getTripListIdlingResource() {
        return mTipDetailsViewModel.getTripListIdlingResource();
    }
}
