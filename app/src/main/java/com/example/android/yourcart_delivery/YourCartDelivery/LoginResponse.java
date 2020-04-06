package com.example.android.yourcart_delivery.YourCartDelivery;

class LoginResponse {
    private String del_boy_phone, del_boy_id, found, del_boy_name;

    public LoginResponse(String del_boy_phone, String del_boy_id, String found) {
        this.del_boy_phone = del_boy_phone;
        this.del_boy_id = del_boy_id;
        this.found = found;
    }

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
