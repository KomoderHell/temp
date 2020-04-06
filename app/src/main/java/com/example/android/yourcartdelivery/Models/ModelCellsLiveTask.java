package com.example.android.yourcartdelivery.Models;

public class ModelCellsLiveTask {
    private  String delivery_lat;
    private String uri;


    public ModelCellsLiveTask() {
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDelivery_lat() {
        return delivery_lat;
    }

    public void setDelivery_lat(String delivery_lat) {
        this.delivery_lat = delivery_lat;
    }

    public String getDelivery_long() {
        return delivery_long;
    }

    public void setDelivery_long(String getDelivery_long) {
        this.delivery_long = getDelivery_long;
    }

    public ModelCellsLiveTask(String delivery_lat, String getDelivery_long) {
        this.delivery_lat = delivery_lat;
        this.delivery_long = getDelivery_long;
    }

    private String delivery_long;
}
