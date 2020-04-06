package com.example.android.yourcartdelivery.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CheckPointResponse {
    @SerializedName("checkpoint_lat")
    String checkpoint_lat;
    @SerializedName("checkpoint_long")
    String checkpoint_lng;

    String cell_lat;
    String cell_lng;
    String checkpoint_vendor_name;
    String checkpoint_vendor_phone;

    @SerializedName("checkpoint_address")
    String checkpoint_address;
    @SerializedName("boy_type")
    String boy_type;

    ArrayList<DelBoyResponse> deliveryBoys;

    public CheckPointResponse(String checkpoint_lat, String checkpoint_lng, String checkpoint_address, String boy_type, ArrayList<DelBoyResponse> deliveryBoys) {
        this.checkpoint_lat = checkpoint_lat;
        this.checkpoint_lng = checkpoint_lng;
        this.checkpoint_address = checkpoint_address;
        this.boy_type = boy_type;
        this.deliveryBoys = deliveryBoys;
    }

    public String getCheckpoint_lat() {
        return checkpoint_lat;
    }

    public void setCheckpoint_lat(String checkpoint_lat) {
        this.checkpoint_lat = checkpoint_lat;
    }

    public String getCheckpoint_lng() {
        return checkpoint_lng;
    }

    public void setCheckpoint_lng(String checkpoint_lng) {
        this.checkpoint_lng = checkpoint_lng;
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

    public String getCheckpoint_vendor_name() {
        return checkpoint_vendor_name;
    }

    public void setCheckpoint_vendor_name(String checkpoint_vendor_name) {
        this.checkpoint_vendor_name = checkpoint_vendor_name;
    }

    public String getCheckpoint_vendor_phone() {
        return checkpoint_vendor_phone;
    }

    public void setCheckpoint_vendor_phone(String checkpoint_vendor_phone) {
        this.checkpoint_vendor_phone = checkpoint_vendor_phone;
    }

    public ArrayList<DelBoyResponse> getDeliveryBoys() {
        return deliveryBoys;
    }

    public void setDeliveryBoys(ArrayList<DelBoyResponse> deliveryBoys) {
        this.deliveryBoys = deliveryBoys;
    }

    public String getCheckpoint_address() {
        return checkpoint_address;
    }

    public void setCheckpoint_address(String checkpoint_address) {
        this.checkpoint_address = checkpoint_address;
    }

    public String getBoy_type() {
        return boy_type;
    }

    public void setBoy_type(String boy_type) {
        this.boy_type = boy_type;
    }
}
