package com.example.android.yourcartdelivery.Models;

import com.google.gson.annotations.SerializedName;

public class VendorDetailRequest {

    @SerializedName("order_type")
        String order_type;
    @SerializedName("order_id")
    String order_id;
    @SerializedName("phone_no")
    String phone_no;

    public VendorDetailRequest(String order_type, String order_id, String phone_no) {
        this.order_type = order_type;
        this.order_id = order_id;
        this.phone_no = phone_no;
    }
}
