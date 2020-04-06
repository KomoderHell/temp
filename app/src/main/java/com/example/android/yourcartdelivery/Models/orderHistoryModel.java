package com.example.android.yourcartdelivery.Models;

import androidx.annotation.NonNull;

public class orderHistoryModel {
    private String order_id;
    private String date;
    private String time;
    private String primary;

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    @NonNull
    @Override
    public String toString() {
        return "Pogo{" +
                "order_id='" + order_id + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                '}';
    }

    public orderHistoryModel(){

    }

    public String getOrder_id() {
        return order_id;
    }

    public String getDate() {
        return date;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public orderHistoryModel(String order_id, String date, String time) {
        this.order_id = order_id;
        this.date = date;
        this.time = time;
    }
}
