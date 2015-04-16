package com.example.miguel.myapplication.util;

import com.google.gson.JsonDeserializationContext;
 import com.google.gson.JsonDeserializer;
 import com.google.gson.JsonElement;

 import java.lang.reflect.Type;
 import java.util.Calendar;

 /**
 * Class who parse a Timestamp long date to Calendar.
 *
 * @author Miguel Francisco García del Moral Munoz.
 */
public class TimestampToCalendarParser implements JsonDeserializer<Calendar> {

    /**
     * This method deserialize the JSON data (long) into a Calendar instance.
     * Note that months in Calendar starts in 0, so January == 0.
     *
     * @param json - The JSON data being deserializer.
     * @param typeOfT - The type of the object to deserialize.
     * @param context - The context.
     * @return - The resultant Calendar object.
     */
    @Override
    public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(json.getAsJsonPrimitive().getAsLong());
        return calendar;
    }
}
