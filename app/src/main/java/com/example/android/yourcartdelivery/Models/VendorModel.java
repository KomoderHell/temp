package com.example.android.yourcartdelivery.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VendorModel implements Serializable {

    @SerializedName("vendor_name")
    private String vendorName;

    @SerializedName("vendor_phone")
    private String phone;
    @SerializedName("vendor_address")
    private String vendor_address;

    private ArrayList<ItemModel> products;
    @SerializedName("is_picked")
    private String isPicked;
    private boolean isPrepared;

    @SerializedName("vendor_lat")
    double vendor_lat;
    @SerializedName("vendor_long")
    double vendor_long;
    @SerializedName("vendor_cell_lat")
    double vendor_cell_lat;
    @SerializedName("vendor_cell_long")
    double vendor_cell_long;
    @SerializedName("vendor_cell_id")
    String vendor_cell_id;

    public VendorModel(String vendorName, String phone, String vendor_address, boolean isPicked, double vendor_lat, double vendor_long, double vendor_cell_lat, double vendor_cell_long, String vendor_cell_id) {
        this.vendorName = vendorName;
        this.phone = phone;
        this.vendor_address = vendor_address;
        this.isPicked = "" + isPicked;
        this.vendor_lat = vendor_lat;
        this.vendor_long = vendor_long;
        this.vendor_cell_lat = vendor_cell_lat;
        this.vendor_cell_long = vendor_cell_long;
        this.vendor_cell_id = vendor_cell_id;
        this.isPrepared = false;
        this.products = new ArrayList<>();
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<ItemModel> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ItemModel> products) {
        this.products = products;
    }

    public String getIsPicked() {
        return isPicked;
    }

    public void setIsPicked(String isPicked) {
        this.isPicked = isPicked;
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void setPrepared(boolean prepared) {
        isPrepared = prepared;
    }

    public String getVendor_address() {
        return vendor_address;
    }

    public void setVendor_address(String vendor_address) {
        this.vendor_address = vendor_address;
    }

    public double getVendor_lat() {
        return vendor_lat;
    }

    public void setVendor_lat(double vendor_lat) {
        this.vendor_lat = vendor_lat;
    }

    public double getVendor_long() {
        return vendor_long;
    }

    public void setVendor_long(double vendor_long) {
        this.vendor_long = vendor_long;
    }

    public double getVendor_cell_lat() {
        return vendor_cell_lat;
    }

    public void setVendor_cell_lat(double vendor_cell_lat) {
        this.vendor_cell_lat = vendor_cell_lat;
    }

    public double getVendor_cell_long() {
        return vendor_cell_long;
    }

    public void setVendor_cell_long(double vendor_cell_long) {
        this.vendor_cell_long = vendor_cell_long;
    }

    public String getVendor_cell_id() {
        return vendor_cell_id;
    }

    public void setVendor_cell_id(String vendor_cell_id) {
        this.vendor_cell_id = vendor_cell_id;
    }
}
