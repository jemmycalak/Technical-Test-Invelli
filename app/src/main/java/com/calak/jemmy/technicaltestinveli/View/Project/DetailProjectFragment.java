/*
 * Copyright (c) 16 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.View.Project;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.calak.jemmy.technicaltestinveli.Adapter.Holder.TaskHolder;
import com.calak.jemmy.technicaltestinveli.Adapter.RecyclerViewAdapter;
import com.calak.jemmy.technicaltestinveli.Data.Online.Online;
import com.calak.jemmy.technicaltestinveli.Listener.OnMenu;
import com.calak.jemmy.technicaltestinveli.Model.mProject;
import com.calak.jemmy.technicaltestinveli.Model.mTasks;
import com.calak.jemmy.technicaltestinveli.R;
import com.calak.jemmy.technicaltestinveli.Utils.TimeFormater;
import com.calak.jemmy.technicaltestinveli.View.Task.DetailTaskFragment;
import com.calak.jemmy.technicaltestinveli.View.Task.NewTaskFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailProjectFragment extends Fragment {

    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.archived)
    EditText archived;
    @BindView(R.id.createAt)
    EditText createAt;
    @BindView(R.id.lTitle)
    TextInputLayout lTitle;
    @BindView(R.id.lArchived)
    TextInputLayout lArchived;
    @BindView(R.id.lCreateAt)
    TextInputLayout lCreateAt;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerTask;
    @BindView(R.id.btEdit)
    FloatingActionButton btEdit;
    @BindView(R.id.swipRefresh)
    SwipeRefreshLayout layout;
    RecyclerViewAdapter adapter;

    public DetailProjectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layoutProject for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_project, container, false);
        ButterKnife.bind(this, view);
        initUI();
//        bindData();

        return view;
    }

    private void initUI() {
        recyclerTask.setHasFixedSize(true);
        recyclerTask.setLayoutManager(new LinearLayoutManager(getContext()));
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bindData();
            }
        });
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("data", modelProject.getTitle());
                NewTaskFragment newTaskFragment = new NewTaskFragment();
                newTaskFragment.setArguments(bundle);
                onMenu.OnMenu(newTaskFragment);
            }
        });
    }
    mProject modelProject;
    private void bindData() {
        Bundle bundle = getArguments();
        modelProject = (mProject) bundle.getSerializable("data");
        title.setText(modelProject.getTitle());
        createAt.setText(TimeFormater.type2(modelProject.getCreateAt(), 0));
        if (modelProject.isArchived()) {
            archived.setText("Archived");
        } else {
            archived.setText("Unarchived");
        }
        GetTask(modelProject.getTitle());
    }

    private void GetTask(String title) {
        layout.setRefreshing(true);
        new Online().GetTasks(getActivity(), DetailProjectFragment.this, title);
    }

    public void onSuccess(ArrayList<mTasks> data) {
        layout.setRefreshing(false);

        adapter = new RecyclerViewAdapter<mTasks, TaskHolder>(R.layout.layout_tasks, data, mTasks.class, TaskHolder.class) {
            @Override
            protected void bindView(TaskHolder holder, final mTasks model, int position) {
                holder.title.setText(model.getTitle());
                try{
                    holder.deadline.setText("Deadline : " + TimeFormater.type2(model.getDeadline(), 0));
                }catch (Exception e){
                    holder.deadline.setText("Deadline : -");
                }
                holder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", model);
                        bundle.putString("project", modelProject.getTitle());
                        DetailTaskFragment detailTaskFragment = new DetailTaskFragment();
                        detailTaskFragment.setArguments(bundle);
                        onMenu.OnMenu(detailTaskFragment);
                    }
                });
                if(model.isDone()){
                    holder.bgTask.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_done));
                }
            }
        };
        recyclerTask.setAdapter(adapter);
    }

    OnMenu onMenu;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMenu){
            onMenu = (OnMenu) context;
        }else{
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        onMenu.OnActionBar("Detail Project");
    }
}
