package com.skyscanner.ui.trip.details;

import android.content.Context;
import android.databinding.ObservableField;
import android.text.Html;

import com.skyscanner.R;
import com.skyscanner.data.model.api.trip.CompleteTrip;
import com.skyscanner.data.model.api.trip.TripItem;
import com.skyscanner.data.model.api.trip.TripItemLeg;
import com.skyscanner.utils.AppConstants;
import com.skyscanner.utils.CommonUtils;

import java.util.List;

/**
 * Created by suyashg on 10/06/18.
 */

public class TripDetailItemViewModel {

    public final ObservableField<String> onewayAirlineName;
    public final ObservableField<String> returnAirlineName;

    public final ObservableField<String> oneWayTimings;
    public final ObservableField<String> returnTimings;

    public final ObservableField<String> onewayDurationAndStops;
    public final ObservableField<String> returnDurationAndStops;

    public final ObservableField<String> onewayCost;
    public final ObservableField<String> returnCost;

    public final ObservableField<String> onewayClass;
    public final ObservableField<String> returnClass;

    private final CompleteTrip mCompleteTrip;

    public TripDetailItemViewModel(Context context, CompleteTrip completeTrip) {
        mCompleteTrip = completeTrip;
        onewayAirlineName = new ObservableField<>(prepareAirlineName(context, mCompleteTrip.getOneWayTrip()));
        returnAirlineName = new ObservableField<>(prepareAirlineName(context, mCompleteTrip.getReturnTrip()));

        oneWayTimings = new ObservableField<>(Html.fromHtml(prepareTimingsLabel(context, mCompleteTrip.getOneWayTrip())).toString());
        returnTimings = new ObservableField<>(Html.fromHtml(prepareTimingsLabel(context, mCompleteTrip.getReturnTrip())).toString());

        onewayDurationAndStops = new ObservableField<>(prepareDurationAndStopsLabel(context, mCompleteTrip.getOneWayTrip()));
        returnDurationAndStops = new ObservableField<>(prepareDurationAndStopsLabel(context, mCompleteTrip.getReturnTrip()));

        onewayCost = new ObservableField<>(prepareCostLabel(context, mCompleteTrip.getOneWayTrip()));
        returnCost = new ObservableField<>(prepareCostLabel(context, mCompleteTrip.getReturnTrip()));

        onewayClass = new ObservableField<>(mCompleteTrip.getOneWayTrip().getTicketClass().getName());
        returnClass = new ObservableField<>(mCompleteTrip.getReturnTrip().getTicketClass().getName());
    }

    private String prepareAirlineName(Context context, TripItem tripItem) {
        List<TripItemLeg> tripItemLegs = tripItem.getTripItemLegs();
        if (tripItemLegs.size() > 1) {
            if (tripItemLegs.size() == 2) {
                String nameA = tripItemLegs.get(0).getAirline().getName();
                String nameB = tripItemLegs.get(1).getAirline().getName();
                if (!nameA.equals(nameB)) {
                    return nameA + AppConstants.TEXT_NEXT_LINE + AppConstants.TEXT_AMPERCENT + nameB;
                } else {
                    return nameA;
                }
            } else {
                return context.getString(R.string.label_multi_airline);
            }
        } else {
            return tripItemLegs.get(0).getAirline().getName();
        }
    }

    private String prepareTimingsLabel(Context context, TripItem tripItem) {
        List<TripItemLeg> tripItemLegs = tripItem.getTripItemLegs();
        String departure = CommonUtils.convertMillisToHoursMinsFormat(context, tripItemLegs.get(0).getDepartureTime());
        String arrival = CommonUtils.convertMillisToHoursMinsFormat(context, tripItemLegs.get(tripItemLegs.size() - 1).getArrivalTime());
        return departure + context.getString(R.string.label_double_arrow) + arrival;
    }

    private String prepareDurationAndStopsLabel(Context context, TripItem tripItem) {
        Long departureTime = tripItem.getTripItemLegs().get(0).getDepartureTime();
        Long arrivalTime = tripItem.getTripItemLegs().get(tripItem.getTripItemLegs().size() - 1).getArrivalTime();

        String duration = CommonUtils.calculateTheDuration(context, departureTime, arrivalTime);
        if (tripItem.getTripItemLegs().size() > 1) {
            String name = tripItem.getTripItemLegs().get(0).getDestination().getName();
            String code = CommonUtils.extractTextInBrackets(name);
            if (!code.equals(AppConstants.TEXT_EMPTY)) {
                duration = duration + context.getString(R.string.label_via) + code;
            }
        } else {
            duration = duration + context.getString(R.string.label_non_stop);
        }
        return duration;
    }

    private String prepareCostLabel(Context context, TripItem tripItem) {
        Float price = tripItem.getOffer().getPrice();
        return String.valueOf((int) Math.ceil(price)) + context.getString(R.string.label_usd);
    }
}
