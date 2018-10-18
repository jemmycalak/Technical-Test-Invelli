/*
 * Copyright (c) 17 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeFormater {

    public static String type2(String dateString, int code) {
        SimpleDateFormat simpleDateFormat = null;
        String pola = null;
        switch (code){
            case 0:
                //////////////////pola/////////////////////////////////
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                pola = "H:mm d-M-yyyy";
                break;
            case 1:
                //////////////////pola/////////////////////////////////
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                pola = "d-M-yyyy";
                break;
            case 2:
                //////////////////pola/////////////////////////////////
                simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                pola= "yyyy-MM-dd'T'HH:mm:ss";
                break;
        }
        Locale locale = null;

        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String tanggalStr = null;
        SimpleDateFormat formatter = null;
        if (locale == null) {
            formatter = new SimpleDateFormat(pola);
        } else {
            formatter = new SimpleDateFormat(pola, locale);
        }
        tanggalStr = formatter.format(date);
        return tanggalStr;
    }
}
