package com.company;

import java.util.Calendar;

public class TimeHelper {
    private static TimeHelper instance;
    private Calendar currentDate;

    public static TimeHelper getInstance(){
        if (instance==null) instance=new TimeHelper();
        return instance;
    }


    public Calendar getCurrentDate() {
        if (currentDate==null) return Calendar.getInstance();
        return currentDate;
    }

    public void setCurrentDate(Calendar currentDate) {
        this.currentDate = currentDate;
    }

    private TimeHelper() {

    }



}
