package com.rent.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mykhailo on 1/9/14.
 */
public class MyDateUtil {
    private static final SimpleDateFormat SDF=new SimpleDateFormat("MM-yyyy");
    private MyDateUtil(){

    }
    public static int monthsDiff(Date startDate,Date endDate){
        Calendar s=Calendar.getInstance();
        Calendar e = Calendar.getInstance();
        s.setTime(startDate);
        e.setTime(endDate);
        return (e.get(Calendar.YEAR)-s.get(Calendar.YEAR))*12+e.get(Calendar.MONTH)-s.get(Calendar.MONTH)+1;
    }

    public static Date setToFirstDayOfMonth(Date date){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH,1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,1);
        return c.getTime();
    }
    public static Date setToLastDayOfMonth(Date date){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        return c.getTime();
    }

    public static boolean checkDates(Date currentDate, Date startDate, Date endDate){
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        return checkDates(c,startDate,endDate);
    }

    public static boolean checkDates(Calendar currentDate, Date startDate, Date endDate){
        Calendar s=Calendar.getInstance();
        Calendar e = Calendar.getInstance();
        s.setTime(startDate);
        e.setTime(endDate);
        int currentMonth=getMonthNumeration(currentDate),startMonth=getMonthNumeration(s),endMonth=getMonthNumeration(e);
        if (currentMonth>startMonth || endMonth<startMonth)
            return false;
        else return true;
    }
    private static int getMonthNumeration(Calendar c){
        return c.get(Calendar.YEAR)*12+c.get(Calendar.MONTH);
    }

    public static String dateToString(Object o){
        if (o instanceof Calendar){
            return SDF.format(((Calendar)o).getTime());
        }
        return SDF.format(o);
    }

    public static Date stringToDate(String s){
        try {
            return SDF.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
