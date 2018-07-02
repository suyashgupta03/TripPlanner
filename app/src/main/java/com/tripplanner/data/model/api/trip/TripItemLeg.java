package com.tripplanner.data.model.api.trip;

/**
 * Created by suyashg on 11/06/18.
 */

public class TripItemLeg {

    private Long departureTime;
    private Long arrivalTime;
    private Airline airline;
    private Airport origin;
    private Airport destination;

    public TripItemLeg(Long departureTime, Long arrivalTime, Airline airline, Airport origin, Airport destination) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public Airline getAirline() {
        return airline;
    }

    public Long getDepartureTime() {
        return departureTime;
    }

    public Long getArrivalTime() {
        return arrivalTime;
    }
}
