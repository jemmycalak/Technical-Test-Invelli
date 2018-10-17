/*
 * Copyright (c) 17 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public abstract class RecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    Class<T> mModel;
    Class<VH> mViewHolder;
    ArrayList<T> mData;
    int mLayout;

    public RecyclerViewAdapter(int layout, ArrayList<T> data, Class<T>model, Class<VH> viewHolder) {
        this.mModel = model;
        this.mViewHolder = viewHolder;
        this.mData = data;
        this.mLayout = layout;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(mLayout, parent, false);
        try{
            Constructor<VH> constructor = (Constructor<VH>) mViewHolder.getConstructor(View.class);
            return constructor.newInstance(viewGroup);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage()+ " onCreatViewHolder");
        }
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        T model = mData.get(position);
        bindView(holder, model, position);
    }

    abstract protected void bindView(VH holder, T model, int position);

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
