package com.example.android.yourcartdelivery.Models;

import com.google.gson.annotations.SerializedName;

public class DelBoyResponse {
    @SerializedName("name")
    private String delBoyName;
    @SerializedName("phone_no")
    private String delBoyPhone;
    @SerializedName("type")
    private String boyType;
    @SerializedName("image")
    private String image;

    public DelBoyResponse(String delBoyName, String delBoyPhone, String boyType, String image) {
        this.delBoyName = delBoyName;
        this.delBoyPhone = delBoyPhone;
        this.boyType = boyType;
        this.image = image;
    }

    public String getDelBoyName() {
        return delBoyName;
    }

    public void setDelBoyName(String delBoyName) {
        this.delBoyName = delBoyName;
    }

    public String getDelBoyPhone() {
        return delBoyPhone;
    }

    public void setDelBoyPhone(String delBoyPhone) {
        this.delBoyPhone = delBoyPhone;
    }

    public String getBoyType() {
        return boyType;
    }

    public void setBoyType(String boyType) {
        this.boyType = boyType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
