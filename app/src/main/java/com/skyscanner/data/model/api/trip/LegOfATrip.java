package com.skyscanner.data.model.api.trip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suyashg on 10/06/18.
 */

public class LegOfATrip {

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("airlineIndex")
    private int airlineIndex;

    @Expose
    @SerializedName("flightNumber")
    private int flightNumber;

    @Expose
    @SerializedName("departure")
    private Long departure;

    @Expose
    @SerializedName("arrival")
    private Long arrival;

    @Expose
    @SerializedName("originIndex")
    private int originIndex;

    @Expose
    @SerializedName("destinationIndex")
    private int destinationIndex;

    public int getAirlineIndex() {
        return airlineIndex;
    }

    public Long getDeparture() {
        return departure;
    }

    public Long getArrival() {
        return arrival;
    }

    public int getOriginIndex() {
        return originIndex;
    }

    public int getDestinationIndex() {
        return destinationIndex;
    }

    public Long getId() {
        return id;
    }
}
