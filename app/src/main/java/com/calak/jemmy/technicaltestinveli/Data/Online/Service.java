/*
 * Copyright (c) 16 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.Data.Online;

import com.calak.jemmy.technicaltestinveli.Model.mProject;
import com.calak.jemmy.technicaltestinveli.Model.mTasks;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Service{


    @GET(Base.GetProjects)
    Call<ArrayList<mProject>> getAllProject();

    @POST(Base.GetProjects)
    Call<mProject> createProject(@Body mProject model);

    @GET(Base.GetProjects+"/{title}"+Base.GetTask)
    Call<ArrayList<mTasks>> getTasks(@Path("title") String title);

    @POST(Base.GetProjects+"/{project}"+Base.GetTask)
    Call<mTasks> createTask(@Path("project") String title, @Body mTasks tasks);

    @DELETE(Base.GetProjects+"/{project}"+Base.GetTask+"/{id}")
    Call<ResponseBody> deleteTask(@Path("project") String project, @Path("id") String id);

    @PUT(Base.GetProjects+"/{project}"+Base.GetTask+"/{id}")
    Call<ResponseBody> updateTask(@Path("project") String project, @Path("id")String id, @Body mTasks tasks);
}
