package com.example.android.yourcartdelivery.Models;

import java.util.ArrayList;

public class OrderHistoryResponse {
    private ArrayList<orderHistoryModel> locations;

    public void setLocations(ArrayList<orderHistoryModel> locations) {
        this.locations = locations;
    }

    public ArrayList<orderHistoryModel> getLocations() {
        return locations;
    }

    public OrderHistoryResponse(ArrayList<orderHistoryModel> locations) {
        this.locations = locations;
    }
}
