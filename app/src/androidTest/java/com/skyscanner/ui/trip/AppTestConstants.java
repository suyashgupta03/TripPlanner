package com.skyscanner.ui.trip;

import java.util.Calendar;

/**
 * Created by suyashg on 13/06/18.
 */

public class AppTestConstants {

    //DEFAULT ERROR WITH MESSAGE
    public static final String ERROR_DEPARTURE_DATE_MSG = "Departure must be a valid date from now";

    public static Calendar getValidDepartureDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 10,10);
        return calendar;
    }

    public static Calendar getInValidDepartureDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 10,10);
        return calendar;
    }
}
