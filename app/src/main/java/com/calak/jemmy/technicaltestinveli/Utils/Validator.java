/*
 * Copyright (c) 17 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.Utils;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.WindowManager;

public class Validator {

    public static void RequestFocus(View view, Activity activity){
        view.clearFocus();
        if (view.requestFocus()) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public static Boolean isText(String s, TextInputLayout sInput, Activity activity){
        if (s.equals("")){
            sInput.setError("Tidak boleh kosong");
            RequestFocus(sInput, activity);
            return false;
        }
        sInput.setErrorEnabled(false);
        return true;
    }

}
