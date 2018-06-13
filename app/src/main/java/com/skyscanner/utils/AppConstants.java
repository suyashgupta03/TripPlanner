package com.skyscanner.utils;

/**
 * Created by suyashg on 12/06/18.
 */

public class AppConstants {

    //HTTP CLIENT CONSTANTS
    public static final String PARAM_CONTENT_TYPE = "Content-Type";

    public static final String PARAM_VALUE_CONTENT_TYPE = "application/json";


    //TEXT UTILS
    public static final String ORIGIN_AIRPORT_CODE = "CPH";

    public static final String DESTINATION_AIRPORT_CODE = "PAR";

    public static final int NUMBER_OF_TICKETS = 1;

    public static final String TEXT_EMPTY = "";

    public static final String TEXT_NEXT_LINE = "\n";

    public static final String TEXT_AMPERCENT = "&"+ TEXT_NEXT_LINE;

    //PARAMS
    public static final String PARAM_TRIP_DATA = "paramTripData";


    //DEFAULT ERROR WITH MESSAGE
    public static final int ERROR_DEFAULT_CODE = -999;

    public static final String ERROR_DEFAULT_STATUS = "Bad Request";

    public static final String ERROR_DEFAULT_MSG = "We are facing some issues. Please try again later";
}
