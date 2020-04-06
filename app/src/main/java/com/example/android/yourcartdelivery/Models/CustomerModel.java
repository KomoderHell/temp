package com.example.android.yourcartdelivery.Models;

import com.google.gson.annotations.SerializedName;

public class CustomerModel {
    @SerializedName("first_name")
    String first_name;
    @SerializedName("last_name")
    String last_name;
    @SerializedName("phone")
    String cust_phone;
    @SerializedName("cust_lat")
    String cust_lat;
    @SerializedName("cust_long")
    String cust_lng;
    @SerializedName("address")
    String cust_address;
    String cell_lat;
    String cell_lng;
    String amt;

    public CustomerModel(String first_name, String last_name, String cust_phone, String cust_lat, String cust_lng, String cust_address) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.cust_phone = cust_phone;
        this.cust_lat = cust_lat;
        this.cust_lng = cust_lng;
        this.cust_address = cust_address;
    }

    public CustomerModel(String first_name, String last_name, String cust_phone, String cust_lat, String cust_lng, String cust_address, String cell_lat, String cell_lng, String amt) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.cust_phone = cust_phone;
        this.cust_lat = cust_lat;
        this.cust_lng = cust_lng;
        this.cust_address = cust_address;
        this.cell_lat = cell_lat;
        this.cell_lng = cell_lng;
        this.amt = amt;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public String getCust_lat() {
        return cust_lat;
    }

    public void setCust_lat(String cust_lat) {
        this.cust_lat = cust_lat;
    }

    public String getCust_lng() {
        return cust_lng;
    }

    public void setCust_lng(String cust_lng) {
        this.cust_lng = cust_lng;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public String getCell_lat() {
        return cell_lat;
    }

    public void setCell_lat(String cell_lat) {
        this.cell_lat = cell_lat;
    }

    public String getCell_lng() {
        return cell_lng;
    }

    public void setCell_lng(String cell_lng) {
        this.cell_lng = cell_lng;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }
}
