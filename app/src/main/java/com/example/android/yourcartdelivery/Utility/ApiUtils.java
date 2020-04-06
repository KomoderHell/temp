package com.example.android.yourcartdelivery.Utility;

public class ApiUtils {
    private ApiUtils() {}
    private static final String BASE_URL = "https://gocoding.azurewebsites.net/";

    public static ApiInterface getAPIService() {
        return ApiManager.getClient(BASE_URL).create(ApiInterface.class);
    }
}
