package com.example.Constants;

import com.example.Models.CompleteOrderListResponse;
import com.example.Models.DeleiveryBoy;
import com.example.Models.ItemSavingResponse;
import com.example.Models.LoginResponse;
import com.example.Models.OrderHistoryResponse;
import com.example.Models.StatusResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("vendor/check/")
    Call<LoginResponse> getLogin(@Field("vendor_id") String vendor_id);

    @GET("vendor/get_products/")
    Call<CompleteOrderListResponse> getallProducts();

    @FormUrlEncoded
    @POST("vendor/get_prev_products/")
    Call<CompleteOrderListResponse> getservingProducts(@Field("vendor_phone") String phone_no);

    @FormUrlEncoded
    @POST("vendor/save_products/")
    Call<ItemSavingResponse> saveMyItems(@Field("vendor_phone") String phone_no, @Field("products") ArrayList<Integer> productId);

    @FormUrlEncoded
    @POST("vendor/history/")
    Call<OrderHistoryResponse> getMyHistory(@Field("vendor_phone") String phone_no);

    @FormUrlEncoded
    @POST("vendor/ongoing/")
    Call<OrderHistoryResponse> getNewOrders(@Field("vendor_phone") String phone_no);

    @FormUrlEncoded
    @POST("vendor/delivery_details/")
    Call<DeleiveryBoy> getDeleiveryBoy(@Field("vendor_phone") String phone_no, @Field("order_id") String order_id);

    @FormUrlEncoded
    @POST("vendor/dispatch/")
    Call<ItemSavingResponse> dispatchOrder(@Field("vendor_phone") String phone_no, @Field("order_id") String order_id);

    @FormUrlEncoded
    @POST("vendor/activate/")
    Call<StatusResponse> setStatus(@Field("vendor_phone") String phone_no, @Field("status") String status);

}
