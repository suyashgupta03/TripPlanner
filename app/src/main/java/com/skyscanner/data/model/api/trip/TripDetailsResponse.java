package com.skyscanner.data.model.api.trip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by suyashg on 10/06/18.
 */

public class TripDetailsResponse {

    @Expose
    @SerializedName("originName")
    private String originName;

    @Expose
    @SerializedName("originCode")
    private String originCode;

    @Expose
    @SerializedName("destinationName")
    private String destinationName;

    @Expose
    @SerializedName("destinationCode")
    private String destinationCode;

    @Expose
    @SerializedName("airlines")
    private List<Airline> airlines;

    @Expose
    @SerializedName("airports")
    private List<Airport> airports;

    @Expose
    @SerializedName("ticketClasses")
    private List<TicketClass> ticketClasses;

    @Expose
    @SerializedName("flights")
    private List<Flight> flights;

    @Expose
    @SerializedName("legs")
    private List<LegOfATrip> legs;

    @Expose
    @SerializedName("segments")
    private List<SegmentOfATrip> segments;

    @Expose
    @SerializedName("suppliers")
    private List<Supplier> suppliers;

    @Expose
    @SerializedName("offers")
    private List<Offer> offers;

    public List<Airline> getAirlines() {
        return airlines;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public List<LegOfATrip> getLegs() {
        return legs;
    }

    public List<SegmentOfATrip> getSegments() {
        return segments;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public List<TicketClass> getTicketClasses() {
        return ticketClasses;
    }
}
