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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.calak.jemmy.technicaltestinveli.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.titleTasks)
    public TextView title;
    @BindView(R.id.deadline)
    public TextView deadline;
    @BindView(R.id.layoutTasks)
    public LinearLayout layout;
    @BindView(R.id.bgTask)
    public ImageView bgTask;

    public TaskHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
