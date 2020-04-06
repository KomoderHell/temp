package com.example.android.yourcartdelivery.Models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("del_boy_phone")
    String del_boy_phone;
    @SerializedName("del_boy_id")
    String del_boy_id;
    @SerializedName("found")
    String found;
    @SerializedName("del_boy_name")
    String del_boy_name;



    public LoginResponse(String del_boy_phone, String del_boy_id, String found, String del_boy_name) {
        this.del_boy_phone = del_boy_phone;
        this.del_boy_id = del_boy_id;
        this.found = found;
        this.del_boy_name = del_boy_name;
    }

    public String getDel_boy_name() {
        return del_boy_name;
    }

    public void setDel_boy_name(String del_boy_name) {
        this.del_boy_name = del_boy_name;
    }

    public String getDel_boy_phone() {
        return del_boy_phone;
    }

    public void setDel_boy_phone(String del_boy_phone) {
        this.del_boy_phone = del_boy_phone;
    }

    public String getDel_boy_id() {
        return del_boy_id;
    }

    public void setDel_boy_id(String del_boy_id) {
        this.del_boy_id = del_boy_id;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }
}
