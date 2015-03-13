package com.devexercise.app.services;

import java.util.Date;

/**
 * Created by prudhvi on 3/9/2015.
 */
public class DataCalculation {

   /* Method to calculate the offset(number of days from
   today to 'change_date' in json data)*/
    public String getDays(long pastTime) {
        Date currentDate = new Date();
        int value = 0; // to store the value of days in terms of weeks/months
        String stringValue = null;
        long currentTime = currentDate.getTime();
        long offSet = currentTime-pastTime;
        int days = (int)offSet/(1000 * 60 * 60 * 24);
        if(days < 14) {
            return "Last week";
        }
        else if(days>=14 && days<30) {
            value = days/7;
            stringValue = Integer.toString(value);
            return stringValue+" weeks ago";
        }
        else if(days>=30 && days<60) {
            return "1 month ago";
        }
        else if(days>=60 && days<=365) {
            value = days/30;
            stringValue = Integer.toString(value);
            return stringValue+" months ago";
        }
        else if(days>365 && days<=730) {
            return "1 year ago";
        }
        else if(days>730) {
            value = days/365;
            stringValue = Integer.toString(value);
            return stringValue+" years ago";
        }
        else
            return null;
    }

    public String getAmount(int amount) {
        float value = amount/1000;
        String stringValue = Float.toString(value);
        return "$"+stringValue+"K";
    }
}
