package com.tripplanner.data.model.api.trip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suyashg on 10/06/18.
 */

public class SegmentOfATrip {

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("duration")
    private int duration;

    @Expose
    @SerializedName("legIndices")
    private Long[] legIndices;

    public int getDuration() {
        return duration;
    }

    public Long getId() {
        return id;
    }

    public Long[] getLegIndices() {
        return legIndices;
    }
}
