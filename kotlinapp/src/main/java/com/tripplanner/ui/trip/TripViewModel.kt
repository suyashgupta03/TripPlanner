package com.tripplanner.ui.trip

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by suyashg on 02/07/18.
 */
class TripViewModel: ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}