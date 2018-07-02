package com.tripplanner.data.model.api.trip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suyashg on 10/06/18.
 */

public class Flight {

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("ticketClassIndex")
    private int ticketClassIndex;

    @Expose
    @SerializedName("segmentIndices")
    private Long[] segmentIndices;

    public int getTicketClassIndex() {
        return ticketClassIndex;
    }

    public Long getId() {
        return id;
    }

    public Long[] getSegmentIndices() {
        return segmentIndices;
    }
}
