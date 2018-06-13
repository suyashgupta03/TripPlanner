package com.skyscanner.data.processing;

import com.skyscanner.data.model.api.trip.CompleteTrip;
import com.skyscanner.data.model.api.trip.Flight;
import com.skyscanner.data.model.api.trip.LegOfATrip;
import com.skyscanner.data.model.api.trip.Offer;
import com.skyscanner.data.model.api.trip.SegmentOfATrip;
import com.skyscanner.data.model.api.trip.Supplier;
import com.skyscanner.data.model.api.trip.TicketClass;
import com.skyscanner.data.model.api.trip.TripDetailsResponse;
import com.skyscanner.data.model.api.trip.TripItem;
import com.skyscanner.data.model.api.trip.TripItemLeg;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import java8.util.J8Arrays;
import java8.util.Optional;
import java8.util.stream.StreamSupport;

/**
 * Created by suyashg on 11/06/18.
 */

public class AppApiProcessor implements ApiProcesser {

    @Inject
    public AppApiProcessor() {
    }

    @Override
    public List<CompleteTrip> processTripDetailsResponse(TripDetailsResponse response) {
        List<CompleteTrip> trips = new ArrayList<>();
        List<SegmentOfATrip> segments = response.getSegments();

        for(Flight flight: response.getFlights()) {
            Long[] segmentIndices = flight.getSegmentIndices();
            // each flight has two segments; a departure and a return in order
            if (segmentIndices.length > 1) {
                List<SegmentOfATrip> segmentOfATrips = new ArrayList<>();
                //match and extract the segments
                J8Arrays.stream(segmentIndices).forEachOrdered(value -> {
                    segmentOfATrips.add(StreamSupport.stream(segments).filter(segmentOfATrip -> segmentOfATrip.getId() == value).findFirst().get()) ;
                });

                //make trip item from the segments which can be used by trip details list items
                TripItem departureSegment = processASegment(segmentOfATrips.get(0), response, flight);
                TripItem returnSegment = processASegment(segmentOfATrips.get(1), response, flight);
                trips.add(new CompleteTrip(departureSegment, returnSegment));
            } else if(segmentIndices.length > 0){
                SegmentOfATrip singleSegmentOfATrip = StreamSupport.stream(segments).filter(segmentOfATrip -> segmentOfATrip.getId() == segmentIndices[0]).findFirst().get();
                TripItem departureSegment = processASegment(singleSegmentOfATrip, response, flight);
                trips.add(new CompleteTrip(departureSegment));
            }
        }
        return trips;
    }

    private TripItem processASegment(SegmentOfATrip segmentOfATrip, TripDetailsResponse response, Flight flight) {
        // legs represent a flight from one airport to another
        Long[] departureLegIndices = segmentOfATrip.getLegIndices();
        if (departureLegIndices.length > 0) {
            Optional<Offer> offer = StreamSupport.stream(response.getOffers())
                    .filter(o -> o.getFlightIndex() == flight.getId()).findFirst();
            Optional<Supplier> supplier = StreamSupport.stream(response.getSuppliers()).filter(o ->
                    J8Arrays.stream(o.getOfferIndices()).anyMatch(m -> m == flight.getId())).findFirst();
            List<LegOfATrip> legsOfATrip = new ArrayList<>();
            //match and extract the legs
            J8Arrays.stream(departureLegIndices).forEachOrdered(value -> {
                legsOfATrip.add(StreamSupport.stream(response.getLegs()).filter(legOfASegment -> legOfASegment.getId() == value).findFirst().get()) ;
            });
            if (offer.isPresent() && supplier.isPresent()) {
                TripItem tripItem = new TripItem();
                List<TripItemLeg> tripItemLegs = new ArrayList<>();
                //make trip item legs by extracting information from services data
                StreamSupport.stream(legsOfATrip).forEachOrdered(legOfATrip -> {
                    // departure and arrival date in milliseconds since start of UNIX epoch
                    tripItemLegs.add(new TripItemLeg(legOfATrip.getDeparture(),
                            legOfATrip.getArrival(),
                            StreamSupport.stream(response.getAirlines()).filter(o -> o.getId() == legOfATrip.getAirlineIndex()).findFirst().get(),
                            StreamSupport.stream(response.getAirports()).filter(o -> o.getId() == legOfATrip.getOriginIndex()).findFirst().get(),
                            StreamSupport.stream(response.getAirports()).filter(o -> o.getId() == legOfATrip.getDestinationIndex()).findFirst().get()
                    ));
                });
                TicketClass ticketClass = StreamSupport.stream(response.getTicketClasses()).filter(o -> o.getId() == flight.getTicketClassIndex()).findFirst().get();
                tripItem.setTicketClass(ticketClass);
                tripItem.setTripItemLegs(tripItemLegs);
                tripItem.setOffer(offer.get());
                tripItem.setSupplier(supplier.get());// suppliers are the ones that sell the tickets
                return tripItem;
            }
        }
        return null;
    }
}
