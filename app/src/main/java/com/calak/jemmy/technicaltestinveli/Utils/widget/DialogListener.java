/*
 * Copyright (c) 18 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.Utils.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogListener {
    public static void AlertOK(Context context, String title, DialogInterface.OnClickListener OK){
        new AlertDialog.Builder(context)
                .setMessage(title)
                .setCancelable(false)
                .setPositiveButton("Ya", OK)
                .create()
                .show();
    }
    public static void AlertOKNO(Context context, String title, DialogInterface.OnClickListener OK, DialogInterface.OnClickListener NO){
        new AlertDialog.Builder(context)
                .setMessage(title)
                .setCancelable(false)
                .setPositiveButton("Ya", OK)
                .setNegativeButton("Tidak", NO)
                .create()
                .show();
    }
}
