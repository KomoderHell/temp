package com.example.android.yourcart_delivery.YourCartDelivery;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface ApiInterface {

    @FormUrlEncoded
    @POST("delivery/check/")
    Call<LoginResponse> getLogin(@Field("del_boy_id") String phone_no);
}
