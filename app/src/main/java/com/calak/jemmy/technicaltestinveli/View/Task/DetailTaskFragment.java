/*
 * Copyright (c) 16 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.View.Task;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.calak.jemmy.technicaltestinveli.Model.mTasks;
import com.calak.jemmy.technicaltestinveli.R;
import com.calak.jemmy.technicaltestinveli.Utils.TimeFormater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailTaskFragment extends Fragment {

    @BindView(R.id.nameTask)
    TextView name;
    @BindView(R.id.createAt)
    TextView craeteAt;
    @BindView(R.id.priority)
    TextView priority;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.deadline)
    TextView deadline;
    @BindView(R.id.btDelete)
    Button btDelet;
    @BindView(R.id.btSave)
    Button btSave;

    public DetailTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layoutProject for this fragment
        View view  = inflater.inflate(R.layout.fragment_detail_task, container, false);
        ButterKnife.bind(this, view);

        bindData();

        return view;
    }

    private void bindData() {
        Bundle bundle = getArguments();
        mTasks m = (mTasks) bundle.getSerializable("data");

        name.setText(": "+m.getTitle());
        craeteAt.setText(": "+TimeFormater.type2(m.getCreateat(), 0));
        priority.setText(": "+m.getPriority());
        if(m.isDone()){
            status.setText(": Selesai");
        }else{
            status.setText(": Belum Selesai");
        }
        try{
            deadline.setText(": "+TimeFormater.type2(m.getDeadline(), 0));
        }catch(Exception e){
            deadline.setText(": -");
        }
    }
}
