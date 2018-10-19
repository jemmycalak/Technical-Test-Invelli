/*
 * Copyright (c) 16 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.View.Task;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.calak.jemmy.technicaltestinveli.Data.Online.Online;
import com.calak.jemmy.technicaltestinveli.Listener.OnMenu;
import com.calak.jemmy.technicaltestinveli.Model.mTasks;
import com.calak.jemmy.technicaltestinveli.R;
import com.calak.jemmy.technicaltestinveli.Utils.TimeFormater;
import com.calak.jemmy.technicaltestinveli.Utils.Validator;
import com.calak.jemmy.technicaltestinveli.Utils.widget.DialogListener;
import com.calak.jemmy.technicaltestinveli.Utils.widget.Loader;
import com.calak.jemmy.technicaltestinveli.Utils.widget.SetEnabled;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailTaskFragment extends Fragment {

    @BindView(R.id.nameTask)
    EditText name;
    @BindView(R.id.createAt)
    EditText craeteAt;
    @BindView(R.id.priority)
    EditText priority;
    @BindView(R.id.status)
    EditText status;
    @BindView(R.id.deadline)
    EditText deadline;

    @BindView(R.id.lNameTask)
    TextInputLayout lName;
    @BindView(R.id.lCreateAt)
    TextInputLayout lCraeteAt;
    @BindView(R.id.lPriority)
    TextInputLayout lPriority;
    @BindView(R.id.lStatus)
    TextInputLayout lStatus;
    @BindView(R.id.lDeadline)
    TextInputLayout lDeadline;

    @BindView(R.id.btDelete)
    Button btDelet;
    @BindView(R.id.btSave)
    Button btSave;

    boolean isEdit = false;
    mTasks m;
    String project;

    public DetailTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layoutProject for this fragment
        View view  = inflater.inflate(R.layout.fragment_detail_task, container, false);
        ButterKnife.bind(this, view);

        initUI();
        bindData();

        return view;
    }
    EditText[] arrayEditText;
    private void initUI() {
        arrayEditText = new EditText[]{
                name, craeteAt, priority, status, deadline
        };
        SetEnabled.SetEnable(arrayEditText, false);

        btSave.setOnClickListener(onSave);
        btDelet.setOnClickListener(onDelete);
    }

    Button.OnClickListener onDelete = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogListener.AlertOKNO(getContext(), "Apakah anda yakin akan menghapus task ini ?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Loader.StartLoad(getContext());
                    new Online().deleteTask(getActivity(), DetailTaskFragment.this, project, m.getId());
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }
    };

    Button.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isEdit){
                if (!Validator.isText(name.getText().toString(), lName, getActivity())){
                    return;
                }
                if(!Validator.isText(priority.getText().toString(), lPriority, getActivity())){
                    return;
                }
                Loader.StartLoad(getContext());
                new Online().updateTask(getActivity(), DetailTaskFragment.this, project, m.getId(), new mTasks(name.getText().toString(), priority.getText().toString()));
            }else{
                btSave.setText(getContext().getResources().getString(R.string.simpan));
                arrayEditText = new EditText[]{
                        name, priority
                };
                SetEnabled.SetEnable(arrayEditText, true);
                isEdit = true;
                setDrawable(getActivity().getResources().getDrawable(R.drawable.ic_save));
            }
        }
    };

    private void setDrawable(Drawable drawable) {
        btSave.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }
    private void bindData() {
        Bundle bundle = getArguments();
        m = (mTasks) bundle.getSerializable("data");
        project = bundle.getString("project");
        name.setText(m.getTitle());
        craeteAt.setText(TimeFormater.type2(m.getCreateat(), 0));
        priority.setText(m.getPriority());
        if(m.isDone()){
            status.setText("Selesai");
        }else{
            status.setText("Belum Selesai");
        }
        try{
            deadline.setText(TimeFormater.type2(m.getDeadline(), 0));
        }catch(Exception e){
            deadline.setText("-");
        }
    }

    OnMenu onMenu;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnMenu){
            onMenu = (OnMenu) context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        onMenu.OnActionBar("Detail Task");
    }

    public void onSuccess() {
        Loader.StopLoad();
        DialogListener.AlertOK(getContext(), "Task berhasil di hapus", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().onBackPressed();
            }
        });
    }

    public void onUpdateSuccess() {
        Loader.StopLoad();
        DialogListener.AlertOK(getContext(), "Task berhasil di ubah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btSave.setText(getContext().getResources().getString(R.string.ubah));
                SetEnabled.SetEnable(arrayEditText, false);
                isEdit = false;
                setDrawable(getActivity().getResources().getDrawable(R.drawable.ic_edit));
            }
        });
    }
}
