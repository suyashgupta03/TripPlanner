package com.tripplanner.ui.trip.details

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by suyashg on 02/07/18.
 */
class TripDetailItemViewModel :ViewModel(), LifecycleObserver {

    private val disposable: CompositeDisposable = CompositeDisposable()



    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}