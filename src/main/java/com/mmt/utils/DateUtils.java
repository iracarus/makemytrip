package com.mmt.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    /**
     * Converts the provided date to the specified format
     * @param dateToConvert
     * @param pattern
     * @return String Date Converted to the provided format
     */
    public static String convertDateToFormat(Date dateToConvert, String pattern)
    {
        //String pattern = "E MMM dd yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String convertedDate = simpleDateFormat.format(dateToConvert);
        return convertedDate;
    }

    /**
     * Adds the specified number of days to the provided date
     * @param date
     * @param daysToAdd
     * @return Date date+DaystoAdd
     */
    public static Date addDays(Date date, int daysToAdd)
    {
        Date newDate;
        Calendar c = Calendar.getInstance();
        c.setTime(date); // Now use today date.
        c.add(Calendar.DATE, daysToAdd); // Adding 5 days
        newDate = c.getTime();
        return newDate;
    }
}
