/*
 * Copyright (c) 18 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.Utils;

import java.util.Calendar;

public class MaxMinDate {
    public static Calendar getMinDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1980, 1, 1);
        return calendar;
    }

    public static Calendar getMaxDate(){
        Calendar now = Calendar.getInstance();
        now.getTime();
        return now;
    }
}
