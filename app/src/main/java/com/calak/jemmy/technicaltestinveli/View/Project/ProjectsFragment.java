/*
 * Copyright (c) 16 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.View.Project;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.calak.jemmy.technicaltestinveli.Adapter.Holder.ProjectsHolder;
import com.calak.jemmy.technicaltestinveli.Adapter.RecyclerViewAdapter;
import com.calak.jemmy.technicaltestinveli.Data.Online.Online;
import com.calak.jemmy.technicaltestinveli.Listener.OnMenu;
import com.calak.jemmy.technicaltestinveli.Model.mProject;
import com.calak.jemmy.technicaltestinveli.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectsFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerProject;
    @BindView(R.id.swipRefresh)
    SwipeRefreshLayout layout;
    @BindView(R.id.swipEmpty)
    SwipeRefreshLayout layoutEmpty;
    RecyclerViewAdapter adapter;
    @BindView(R.id.btNew)
    ImageView btNew;
    @BindView(R.id.btArchived)
    ImageView btArchived;
    @BindView(R.id.btUnarchived)
    ImageView btUnarchived;
    @BindView(R.id.btSort)
    ImageView btSort;
    @BindView(R.id.btFilter)
    ImageView btFilter;

    public ProjectsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_projects, container, false);
        ButterKnife.bind(this, view);
        initUI();
        GetData();
        return view;
    }

    private void initUI() {
        recyclerProject.setHasFixedSize(true);
        recyclerProject.setLayoutManager(new LinearLayoutManager(getActivity()));

        layout.setColorSchemeResources(R.color.colorPrimary);
        layoutEmpty.setColorSchemeResources(R.color.colorPrimary);
        layout.setOnRefreshListener(onRefresh);
        layoutEmpty.setOnRefreshListener(onRefresh);
        btNew.setOnClickListener(this);
        btArchived.setOnClickListener(this);
        btUnarchived.setOnClickListener(this);
        btSort.setOnClickListener(this);
        btFilter.setOnClickListener(this);
    }

    private void GetData() {
        layout.setRefreshing(true);
        new Online().GetDataProjects(getActivity(), ProjectsFragment.this);
    }

    public void SetDataProject(ArrayList<mProject> data){
        layout.setRefreshing(false);
        layoutEmpty.setRefreshing(false);

        adapter = new RecyclerViewAdapter<mProject, ProjectsHolder>( R.layout.layout_projects, data, mProject.class, ProjectsHolder.class) {
            @Override
            protected void bindView(ProjectsHolder holder, mProject model, int position) {
                holder.title.setText(model.getTitle());
                holder.createAt.setText(model.getCreateAt());
            }
        };
        recyclerProject.setAdapter(adapter);
    }

    SwipeRefreshLayout.OnRefreshListener onRefresh = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            GetData();
        }
    };

    OnMenu onMenu;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnMenu){
            onMenu = (OnMenu) context;
        }else{
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btArchived:
                Toast.makeText(getActivity(), "Development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btUnarchived:
                Toast.makeText(getActivity(), "Development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btSort:
                Toast.makeText(getActivity(), "Development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btFilter:
                Toast.makeText(getActivity(), "Development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btNew:
                onMenu.OnMenu(new NewProjectFragment());
                break;
        }
    }
}
