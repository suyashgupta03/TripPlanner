package com.tripplanner.ui.trip

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.tripplanner.R
import com.tripplanner.databinding.ActivityTripBinding
import com.tripplanner.ui.base.BaseActivity
import com.tripplanner.ui.trip.details.TripDetailsFragment
import com.tripplanner.utils.ext.setFragment

class TripActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, TripActivity::class.java))
        }
    }

    val binding: ActivityTripBinding by lazy {
        DataBindingUtil.setContentView<ActivityTripBinding>(this, R.layout.activity_trip)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = binding.flTripDetailsContainer.id;
        setFragment(id, ::TripDetailsFragment)
    }
}
