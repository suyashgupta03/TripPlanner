package com.tripplanner.utils;

import android.content.Context;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by suyashg on 09/06/18.
 */

public class CommonUtils {

    private static final String DATE_FORMAT = "HH:mm";
    private static final String DATE_FORMAT_DURATION_HOURS = "HH";
    private static final String DATE_FORMAT_DURATION_MINS = "mm";
    private static final String SUFFIX_HOUR = "h ";
    private static final String SUFFIX_MINUTES = "m";
    private static final String DATE_PICKER_FORMAT = "d MMM yyyy";

    private CommonUtils() {
        // This class is not publicly instantiable
    }

    public static String convertMillisToHoursMinsFormat(Context context, long millis) {
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, getCurrentLocale(context));
        return sdf.format(date);
    }

    public static String calculateTheDuration(Context context, long millisA, long millisB) {
        Date date = new Date(millisB - millisA);
        SimpleDateFormat sdfHrs = new SimpleDateFormat(DATE_FORMAT_DURATION_HOURS, getCurrentLocale(context));
        SimpleDateFormat sdfMins = new SimpleDateFormat(DATE_FORMAT_DURATION_MINS, getCurrentLocale(context));
        String duration = sdfHrs.format(date) + SUFFIX_HOUR + sdfMins.format(date) + SUFFIX_MINUTES;
        return duration;
    }

    public static String extractTextInBrackets(String text) {
        Matcher m = Pattern.compile("\\((.*?)\\)").matcher(text);
        while (m.find()) {
            return m.group(1);
        }
        return AppConstants.TEXT_EMPTY;
    }

    public static Locale getCurrentLocale(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }

    public static String prepareDateFromValues(Locale locale, Calendar c) {
        return prepareDateFromValues(locale, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    public static String prepareCurrentDate(Locale locale) {
        final Calendar c = Calendar.getInstance();
        return prepareDateFromValues(locale, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    public static String prepareDateFromValues(Locale locale, int year, int month, int day) {
        final Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PICKER_FORMAT, locale);
        return sdf.format(cal.getTime());
    }

}
