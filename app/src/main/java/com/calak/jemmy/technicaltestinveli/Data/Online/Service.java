/*
 * Copyright (c) 16 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.Data.Online;

import com.calak.jemmy.technicaltestinveli.Model.mProject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service{

    @GET(Base.GetProjects)
    Call<ArrayList<mProject>> getAllProject();


}
