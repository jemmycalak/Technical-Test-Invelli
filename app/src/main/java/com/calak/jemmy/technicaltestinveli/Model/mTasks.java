/*
 * Copyright (c) 17 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class mTasks implements Serializable {
    @SerializedName("ID")
    String id;
    @SerializedName("CreatedAt")
    String createat;
    @SerializedName("title")
    String title;
    @SerializedName("deadline")
    String deadline;
    @SerializedName("done")
    boolean done;
    @SerializedName("priority")
    String priority;

    public mTasks(String title, String priority, String deadline) {
        this.title = title;
        this.priority = priority;
        this.deadline = deadline;
    }

    public mTasks(String title, String priority) {
        this.title = title;
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    public String getId() {
        return id;
    }

    public String getCreateat() {
        return createat;
    }

    public String getTitle() {
        return title;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isDone() {
        return done;
    }
}
