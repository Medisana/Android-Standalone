package com.example.miguel.myapplication.util;

import java.util.Calendar;

/**
 * This class use a Calendar instance in order to generate a string YYYY/MM/DD HH:MM.
 *
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class CalendarString {

    public static String calendarToString (Calendar c) {
        return c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.YEAR) + " "
                + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE);
    }
}
