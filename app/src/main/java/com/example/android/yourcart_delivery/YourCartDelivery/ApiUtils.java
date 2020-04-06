package com.example.android.yourcart_delivery.YourCartDelivery;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "https://lit-earth-71252.herokuapp.com/";

    public static ApiInterface getAPIService() {

        return ApiManager.getClient(BASE_URL).create(ApiInterface.class);
    }
}
