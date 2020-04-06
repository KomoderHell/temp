package com.example.android.yourcartdelivery.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VendorDetailResponse {

    @SerializedName("details")
    ArrayList<VendorModel> details;

    public ArrayList<VendorModel> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<VendorModel> details) {
        this.details = details;
    }

    public VendorDetailResponse(ArrayList<VendorModel> details) {
        this.details = details;
    }
}
