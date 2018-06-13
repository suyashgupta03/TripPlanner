package com.skyscanner.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.skyscanner.data.DataManager;
import com.skyscanner.utils.rx.SchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by suyashg on 09/06/18.
 */

public abstract class BaseViewModel<N> extends ViewModel {

    private final SchedulerProvider mSchedulerProvider;

    private final DataManager mDataManager;

    public BaseViewModel(SchedulerProvider mSchedulerProvider, DataManager mDataManager) {
        this.mSchedulerProvider = mSchedulerProvider;
        this.mDataManager = mDataManager;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    private WeakReference<N> mNavigator;

    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);

    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }
}
