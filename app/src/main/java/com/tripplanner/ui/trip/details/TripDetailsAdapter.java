package com.tripplanner.ui.trip.details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tripplanner.data.model.api.trip.CompleteTrip;
import com.tripplanner.databinding.ItemTripDetailBinding;
import com.tripplanner.databinding.ItemTripEmptyViewBinding;
import com.tripplanner.ui.base.BaseViewHolder;

import java.util.List;

/**
 * Created by suyashg on 10/06/18.
 */

public class TripDetailsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<CompleteTrip> mCompleteTrips;

    private TripAdapterListener mListener;

    private String errorMsg;

    public TripDetailsAdapter(List<CompleteTrip> mCompleteTrips) {
        this.mCompleteTrips = mCompleteTrips;
    }

    @Override
    public int getItemCount() {
        if (mCompleteTrips != null && mCompleteTrips.size() > 0) {
            return mCompleteTrips.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mCompleteTrips != null && !mCompleteTrips.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemTripDetailBinding itemTripDetailBinding = ItemTripDetailBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new TripDetailViewHolder(parent.getContext(), itemTripDetailBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemTripEmptyViewBinding emptyViewBinding = ItemTripEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<CompleteTrip> completeTripList) {
        this.mCompleteTrips.addAll(completeTripList);
        notifyDataSetChanged();
    }

    public void clearItemsForError(String errorMsg) {
        this.errorMsg = errorMsg;
        this.mCompleteTrips.clear();
        notifyDataSetChanged();
    }

    public void setListener(TripAdapterListener listener) {
        this.mListener = listener;
    }

    public interface TripAdapterListener {

        void onRetryClick();
    }

    public class TripDetailViewHolder extends BaseViewHolder {

        private ItemTripDetailBinding mBinding;

        private TripDetailItemViewModel mTripItemViewModel;

        private Context mContext;

        public TripDetailViewHolder(Context context, ItemTripDetailBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.mContext = context;
        }

        @Override
        public void onBind(int position) {
            mTripItemViewModel = new TripDetailItemViewModel(mContext, mCompleteTrips.get(position));
            mBinding.setViewModel(mTripItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements TripDetailsEmptyItemViewModel.TripDetailsEmptyItemViewModelListener {

        private ItemTripEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemTripEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            TripDetailsEmptyItemViewModel emptyItemViewModel = new TripDetailsEmptyItemViewModel(this, errorMsg);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            if (mListener != null) {
                mListener.onRetryClick();
            }
        }
    }
}