package com.example.android.yourcartdelivery.Models;

import com.google.gson.annotations.SerializedName;

public class orderHistoryRequestModel {
    @SerializedName("id")
    private String id;

    public orderHistoryRequestModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
