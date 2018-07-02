package com.tripplanner.data.model.api.trip;

import java.util.List;

/**
 * Created by suyashg on 11/06/18.
 */

public class TripItem {

    private String duration;
    private Offer offer;
    private Supplier supplier;
    private List<TripItemLeg> tripItemLegs;
    private TicketClass ticketClass;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<TripItemLeg> getTripItemLegs() {
        return tripItemLegs;
    }

    public void setTripItemLegs(List<TripItemLeg> tripItemLegs) {
        this.tripItemLegs = tripItemLegs;
    }

    public TicketClass getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(TicketClass ticketClass) {
        this.ticketClass = ticketClass;
    }
}
