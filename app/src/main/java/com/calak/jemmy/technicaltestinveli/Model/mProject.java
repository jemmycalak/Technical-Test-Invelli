/*
 * Copyright (c) 16 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.Model;

import com.google.gson.annotations.SerializedName;

public class mProject {
    @SerializedName("ID")
    String id;
    @SerializedName("CreateAt")
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
