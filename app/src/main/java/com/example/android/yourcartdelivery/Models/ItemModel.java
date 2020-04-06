package com.example.android.yourcartdelivery.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemModel implements Serializable {

    @SerializedName("product_id")
    private String productId;
    @SerializedName("product_name")
    private String productName;
    @SerializedName("quantity")
    private String quantity;
    private String isReceived;

    public ItemModel(String productId, String productName, String quantity) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.isReceived = "false";
    }

    public ItemModel(String productId, String productName) {
        this.productId = productId;
        this.productName = productName;
        this.isReceived="false";
        this.quantity = "0";
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getIsReceived() {
        return isReceived;
    }

    public void setIsReceived(String isReceived) {
        this.isReceived = isReceived;
    }
}
