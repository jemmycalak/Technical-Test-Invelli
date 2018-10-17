/*
 * Copyright (c) 16 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.Data.Online;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.calak.jemmy.technicaltestinveli.Model.mProject;
import com.calak.jemmy.technicaltestinveli.Utils.RetrofitInstance;
import com.calak.jemmy.technicaltestinveli.View.Project.ProjectsFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Online {
    Service service = RetrofitInstance.getInstance().create(Service.class);

    public void GetDataProjects(final Activity activity, final Fragment fragment){
        Call<ArrayList<mProject>> call = service.getAllProject();
        call.enqueue(new Callback<ArrayList<mProject>>() {
            @Override
            public void onResponse(Call<ArrayList<mProject>> call, Response<ArrayList<mProject>> response) {
                Log.d("Response", response.isSuccessful()+"-"+response.code());
                if (response.isSuccessful()){
                    ((ProjectsFragment)fragment).SetDataProject(response.body());
                }else{
                    Log.d("Response", "notSuccess");
                }
            }
            @Override
            public void onFailure(Call<ArrayList<mProject>> call, Throwable t) {
                Log.d("ResponseError", t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
