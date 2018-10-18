/*
 * Copyright (c) 16 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.View.Task;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calak.jemmy.technicaltestinveli.Data.Online.Online;
import com.calak.jemmy.technicaltestinveli.Model.mTasks;
import com.calak.jemmy.technicaltestinveli.R;
import com.calak.jemmy.technicaltestinveli.Utils.MaxMinDate;
import com.calak.jemmy.technicaltestinveli.Utils.TimeFormater;
import com.calak.jemmy.technicaltestinveli.Utils.Validator;
import com.calak.jemmy.technicaltestinveli.Utils.widget.DialogListener;
import com.calak.jemmy.technicaltestinveli.Utils.widget.Loader;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewTaskFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.nameTask)
    EditText name;
    @BindView(R.id.priority)
    EditText priority;
    @BindView(R.id.deadline)
    EditText deadline;
    @BindView(R.id.lNameTask)
    TextInputLayout lNameTask;
    @BindView(R.id.lPriority)
    TextInputLayout lPriority;
    @BindView(R.id.lDeadline)
    TextInputLayout lDeadline;
    @BindView(R.id.btSave)
    Button btSave;

    Bundle bundle;
    String title, date=null;

    public NewTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layoutProject for this fragment
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);

        initData();
        initUI();

        return view;
    }

    private void initUI() {
        btSave.setOnClickListener(onSave);
        deadline.setOnClickListener(onDeadline);
    }

    Button.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!Validator.isText(name.getText().toString(), lNameTask, getActivity())){
                return;
            }
            if(!Validator.isText(priority.getText().toString(), lPriority, getActivity())){
                return;
            }
            DoSave();
        }
    };
    EditText.OnClickListener onDeadline = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openDate();
        }
    };

    private void openDate() {
        DatePickerDialog date = DatePickerDialog.newInstance(this,
                new MaxMinDate().getMinDate().get(Calendar.YEAR),
                new MaxMinDate().getMinDate().get(Calendar.MONTH),
                new MaxMinDate().getMinDate().get(Calendar.DAY_OF_MONTH));

        date.setMinDate(new MaxMinDate().getMaxDate());
        date.show(getActivity().getFragmentManager(), "");
    }

    private void DoSave() {
        Log.d("do","<<<<<"+TimeFormater.type2(date, 2));

        Loader.StartLoad(getContext());
        new Online().CreateTask(getActivity(), NewTaskFragment.this, title, new mTasks(name.getText().toString(), priority.getText().toString(), TimeFormater.type2(date, 2)+".111Z"));
    }

    private void initData() {
        bundle = getArguments();
        title = bundle.getString("data");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        date =dayOfMonth+"-"+monthOfYear+"-"+year;
        deadline.setText(date);
    }

    public void onSuccess() {
        Loader.StopLoad();
        DialogListener.AlertOK(getContext(), "Berhasil menambahkan task.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().onBackPressed();
            }
        });
    }

}