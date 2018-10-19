/*
 * Copyright (c) 18 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.Utils.widget;

import android.view.View;

import java.util.ArrayList;

public class SetEnabled {
    public static void SetEnable(View[] views, boolean stts){
        for (int i=0; i<views.length; i++){
            views[i].setEnabled(stts);
        }
    }
}
