package com.blacksystem.automation.application.common;

import com.blacksystem.automation.application.utils.DatesUtils;

import java.util.Date;
import java.util.List;

public class StaticMain {

    public static void main(String[] args){

        //testConvertDateToString();
        //testConvertStringToDate();
        //testGetDateBetweenToString();
        testChangeDateFormat();
        //List list = DatesUtils.getAllDayBetweenDates("01/01/2020","01/31/2020");
        //System.out.println(list);

    }

    public static void testConvertDateToString(){
        String todayDate = DatesUtils.convertDateToString(new Date(), DatesUtils.US_DATE);
        System.out.println(todayDate);
    }

    public static void testConvertStringToDate(){
        Date date = DatesUtils.convertStringToDate("11/11/1980", DatesUtils.US_DATE);
        System.out.println(date);
    }

    public static void testGetDateBetweenToString(){
        String date = DatesUtils.getDateBetweenToString("01/01/2020","01/31/2020", DatesUtils.US_DATE);
        System.out.println(date);
    }

    public static void testChangeDateFormat(){
        System.out.println(DatesUtils.changeDateFormat("01/11/2020","yyyy-dd-MM"));
    }

}
