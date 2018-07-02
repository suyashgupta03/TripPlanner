package com.tripplanner.data.model.api.trip;

/**
 * Created by suyashg on 11/06/18.
 */

public class CompleteTrip {

    private TripItem oneWayTrip;
    private TripItem returnTrip;

    public CompleteTrip(TripItem oneWayTrip, TripItem returnTrip) {
        this.oneWayTrip = oneWayTrip;
        this.returnTrip = returnTrip;
    }

    public CompleteTrip(TripItem oneWayTrip) {
        this.oneWayTrip = oneWayTrip;
    }

    public TripItem getOneWayTrip() {
        return oneWayTrip;
    }

    public TripItem getReturnTrip() {
        return returnTrip;
    }

}
