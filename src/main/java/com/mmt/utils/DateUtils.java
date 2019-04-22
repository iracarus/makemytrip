package com.mmt.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static String convertDateToFormat(Date dateToConvert, String pattern)
    {
        //String pattern = "E MMM dd yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String convertedDate = simpleDateFormat.format(dateToConvert);
        return convertedDate;
    }

    public static Date addDays(Date currentDate, int daysToAdd)
    {
        Date newDate;
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate); // Now use today date.
        c.add(Calendar.DATE, daysToAdd); // Adding 5 days
        newDate = c.getTime();
        return newDate;
    }
}
