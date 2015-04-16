package com.example.miguel.myapplication.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;

/**
 * @author Miguel Francisco García del Moral Muñoz.
 */
public class CustomGson {

    /**
     * Generate a custom Gson who filter the long timestamp into Calendar instances. This is so because
     * the server send the date information as timestamp, that is a long, so its better to parse
     * into a Calendar object in order to work with it easier.
     *
     * @return - Custom Gson.
     */
    public static Gson generateCustomGson () {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Calendar.class, new TimestampToCalendarParser());
        return builder.create();
    }
}
