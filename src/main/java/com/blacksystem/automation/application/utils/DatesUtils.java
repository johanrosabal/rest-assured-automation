package com.blacksystem.automation.application.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DatesUtils {

    public static final String US_DATE = "MM-dd-yyyy";

    public static String convertDateToString(Date date,String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date convertStringToDate(String date, String format){
        try {
            date = date.replace("/","-");
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String changeDateFormat(String date,String format){
        date = date.replace("/","-");
        try {
            Date date1 =  new SimpleDateFormat(format).parse(date);
            return convertDateToString(date1,format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDateBetweenToDate(String date1, String date2, String format){
        Date sDate1 = convertStringToDate(date1,format);
        Date sDate2 = convertStringToDate(date2,format);
        assert sDate1 != null;
        assert sDate2 != null;
        return new Date(ThreadLocalRandom.current().nextLong(sDate1.getTime(), sDate2.getTime()));
    }

    public static String getDateBetweenToString(String date1, String date2, String format){
        return convertDateToString(getDateBetweenToDate(date1, date2,format),format);
    }

    public static List<LocalDate> getAllDayBetweenDates(String startString, String endString){

        startString = startString.replace("/","-");
        endString   = endString.replace("/","-");

//        Date d1     = convertStringToDate(startString,US_DATE);
//        String d2   = convertDateToString(d1,US_DATE);
//        Date d3     = convertStringToDate(d2,US_DATE);
//        startString  = convertDateToString(d3,US_DATE);

        LocalDate incrementingDate = LocalDate.parse(startString);
        LocalDate endDate = LocalDate.parse(endString);

        List<LocalDate> allDates = new ArrayList<>();

        if (incrementingDate.isAfter(endDate)) {
            throw new IllegalStateException("start date must be before or equal to end date");
        }

        while (!incrementingDate.isAfter(endDate)) {
            allDates.add(incrementingDate);
            incrementingDate = incrementingDate.plusDays(1);
        }

        return allDates;
    }
}
