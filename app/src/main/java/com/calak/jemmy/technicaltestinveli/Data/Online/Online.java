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
import com.calak.jemmy.technicaltestinveli.Model.mTasks;
import com.calak.jemmy.technicaltestinveli.Utils.RetrofitInstance;
import com.calak.jemmy.technicaltestinveli.Utils.widget.Loader;
import com.calak.jemmy.technicaltestinveli.View.Project.DetailProjectFragment;
import com.calak.jemmy.technicaltestinveli.View.Project.NewProjectFragment;
import com.calak.jemmy.technicaltestinveli.View.Project.ProjectsFragment;
import com.calak.jemmy.technicaltestinveli.View.Task.DetailTaskFragment;
import com.calak.jemmy.technicaltestinveli.View.Task.NewTaskFragment;

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
                Log.d("Response", response.isSuccessful()+"-"+response.code()+"-"+response.body());
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

    public  void PostData(mProject title, Activity activity, final Fragment fragment){
        Call call = service.createProject(title);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("Response", response.body()+"-"+response.message()+"-"+response.isSuccessful());
                if (response.isSuccessful()){
                    ((NewProjectFragment)fragment).onSucess();
                }else{

                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                Log.d("ResponseError", t.getMessage());
            }
        });
    }

    public void GetTasks(Activity activity, final Fragment fragment, String title){

        Call<ArrayList<mTasks>> call = service.getTasks(title);
        call.enqueue(new Callback<ArrayList<mTasks>>() {
            @Override
            public void onResponse(Call<ArrayList<mTasks>> call, Response<ArrayList<mTasks>> response) {
                Log.d("Response", response.body()+"-"+response.isSuccessful());
                ((DetailProjectFragment)fragment).onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<mTasks>> call, Throwable t) {
                Log.d("ResponsError", t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void CreateTask(Activity activity, final Fragment fragment, String title, mTasks tasks){
        Call call = service.createTask( title, tasks);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("Response", response.isSuccessful()+"-"+response.errorBody()+"-"+response.toString());
                if(response.isSuccessful()){
                    ((NewTaskFragment)fragment).onSuccess();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("ResponseError", t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void deleteTask(Activity activity, final Fragment fragment, String title, String id){
        Call call = service.deleteTask(title, id);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("Response", response.body()+"-"+response.message()+"-"+response.isSuccessful());
                if(response.isSuccessful()){
                    ((DetailTaskFragment) fragment).onSuccess();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("ResponseError", t.getMessage());
                t.printStackTrace();
                Loader.StopLoad();
            }
        });
    }

    public  void updateTask(Activity activity, final Fragment fragment, String project, String id, mTasks tasks){
        Call call = service.updateTask(project, id, tasks);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()) {
                    ((DetailTaskFragment)fragment).onUpdateSuccess();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("ResponseError", t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
