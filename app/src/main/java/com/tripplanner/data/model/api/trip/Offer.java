package com.tripplanner.data.model.api.trip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suyashg on 10/06/18.
 */

public class Offer {

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("price")
    private Float price;

    @Expose
    @SerializedName("flightIndex")
    private int flightIndex;

    @Expose
    @SerializedName("ticketClassIndex")
    private int ticketClassIndex;

    public int getFlightIndex() {
        return flightIndex;
    }

    public Float getPrice() {
        return price;
    }
}
