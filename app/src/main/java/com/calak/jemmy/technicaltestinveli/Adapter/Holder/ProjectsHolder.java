/*
 * Copyright (c) 17 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.Adapter.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.calak.jemmy.technicaltestinveli.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.titleProject)
    public TextView title;
    @BindView(R.id.createAt)
    public TextView createAt;

    public ProjectsHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
