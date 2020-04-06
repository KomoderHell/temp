package com.example.android.yourcart_delivery.YourCartDelivery;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class UserClient extends Application {

    String phoneno="";
    String deliName="", deliId = "";
    public String status = "active";
    private float vendor_lat = 59.030f;
    private float vendor_long = 78.090f;
    private float cust_lat = 59.030f;
    private float cust_long = 78.090f;
    private float check_lat = 59.030f;
    private float check_long = 78.090f;
    private boolean isOrder = false;
    private List<String> vendor_name = new ArrayList<>();
    private List<String> vendor_address = new ArrayList<>();
    private String deliveryType="";
    private String deliveryPos="";

    public List<String> getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(List<String> vendor_name) {
        this.vendor_name = vendor_name;
    }

    public List<String> getVendor_address() {
        return vendor_address;
    }

    public void setVendor_address(List<String> vendor_address) {
        this.vendor_address = vendor_address;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDeliveryPos() {
        return deliveryPos;
    }

    public void setDeliveryPos(String deliveryPos) {
        this.deliveryPos = deliveryPos;
    }

    public float getVendor_lat() {
        return vendor_lat;
    }

    public void setVendor_lat(float vendor_lat) {
        this.vendor_lat = vendor_lat;
    }

    public float getVendor_long() {
        return vendor_long;
    }

    public void setVendor_long(float vendor_long) {
        this.vendor_long = vendor_long;
    }

    public float getCust_lat() {
        return cust_lat;
    }

    public void setCust_lat(float cust_lat) {
        this.cust_lat = cust_lat;
    }

    public float getCust_long() {
        return cust_long;
    }

    public void setCust_long(float cust_long) {
        this.cust_long = cust_long;
    }

    public float getCheck_lat() {
        return check_lat;
    }

    public void setCheck_lat(float check_lat) {
        this.check_lat = check_lat;
    }

    public float getCheck_long() {
        return check_long;
    }

    public void setCheck_long(float check_long) {
        this.check_long = check_long;
    }

    public boolean isOrder() {
        return isOrder;
    }

    public void setOrder(boolean order) {
        isOrder = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getDeliName() {
        return deliName;
    }

    public void setDeliName(String deliName) {
        this.deliName = deliName;
    }

    public String getDeliId() {
        return deliId;
    }

    public void setDeliId(String deliId) {
        this.deliId = deliId;
    }

    public UserClient(String phoneno, String deliName, String deliId) {
        this.phoneno = phoneno;
        this.deliName = deliName;
        this.deliId = deliId;
    }

    public UserClient(String phoneno) {
        this.phoneno = phoneno;
    }

    public UserClient() {
    }
}
