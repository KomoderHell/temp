package com.example.android.yourcartdelivery.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CellModel implements Serializable {
    private String lat, lng;
    private String id;
    private ArrayList<VendorModel> vendors = new ArrayList<>();
    private boolean isCompleted;

    public CellModel( String id, String lat, String lng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.isCompleted = false;
    }

    public CellModel(String id,String lat, String lng, ArrayList<VendorModel> vendors) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.vendors = vendors;
        this.isCompleted = false;
    }

    public void addVendor(VendorModel v){
        vendors.add(v);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public ArrayList<VendorModel> getVendors() {
        return vendors;
    }

    public void setVendors(ArrayList<VendorModel> vendors) {
        this.vendors = vendors;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getNoOfVendors() {
        return vendors.size();
    }

}
