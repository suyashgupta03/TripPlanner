package com.skyscanner.ui.trip.details;

import android.databinding.ObservableField;

/**
 * Created by suyashg on 10/06/18.
 */

public class TripDetailsEmptyItemViewModel {

    private TripDetailsEmptyItemViewModelListener mListener;
    public final ObservableField<String> errorMsg;

    public TripDetailsEmptyItemViewModel(TripDetailsEmptyItemViewModelListener listener, String errorMsg) {
        this.mListener = listener;
        this.errorMsg = new ObservableField<>(errorMsg);
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface TripDetailsEmptyItemViewModelListener {

        void onRetryClick();
    }
}
