/*
 * Copyright (c) 16 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class mProject implements Serializable {
    @SerializedName("ID")
    String id;
    @SerializedName("CreatedAt")
    String createAt;
    @SerializedName("title")
    String title;
    @SerializedName("archived")
    boolean archived;

    public mProject(String id, String createAt, String title, boolean archived) {
        this.id = id;
        this.createAt = createAt;
        this.title = title;
        this.archived = archived;
    }

    public mProject(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getTitle() {
        return title;
    }

    public boolean isArchived() {
        return archived;
    }
}
