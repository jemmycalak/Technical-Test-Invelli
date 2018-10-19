/*
 * Copyright (c) 17 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.View.Project;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.calak.jemmy.technicaltestinveli.Data.Online.Online;
import com.calak.jemmy.technicaltestinveli.Listener.OnMenu;
import com.calak.jemmy.technicaltestinveli.Model.mProject;
import com.calak.jemmy.technicaltestinveli.R;
import com.calak.jemmy.technicaltestinveli.Utils.Validator;
import com.calak.jemmy.technicaltestinveli.Utils.widget.DialogListener;
import com.calak.jemmy.technicaltestinveli.Utils.widget.Loader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewProjectFragment extends Fragment {

    @BindView(R.id.titleProject)
    EditText title;
    @BindView(R.id.lTitle)
    TextInputLayout lTitle;
    @BindView(R.id.btNewProject)
    Button btNew;

    public NewProjectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layoutProject for this fragment
        View view = inflater.inflate(R.layout.fragment_new_project, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        btNew.setOnClickListener(onNew);
    }

    Button.OnClickListener onNew = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String a =title.getText().toString();
            if(Validator.isText(a, lTitle, getActivity())){
                DoCreate(a);
            }
        }
    };

    private void DoCreate(String a) {
        Loader.StartLoad(getContext());
        new Online().PostData(new mProject(a), getActivity(), NewProjectFragment.this);
    }

    public void onSucess(){
        Loader.StopLoad();
        DialogListener.AlertOK(getContext(), "Berhasil menambahkan project.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().onBackPressed();
            }
        });
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
        onMenu.OnActionBar("Tambah Project");
    }
}
