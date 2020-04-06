package com.example.android.yourcartdelivery.Utility;

import com.example.android.yourcartdelivery.Models.CheckPointResponse;
import com.example.android.yourcartdelivery.Models.CustomerModel;
import com.example.android.yourcartdelivery.Models.ItemModel;
import com.example.android.yourcartdelivery.Models.VendorDetailRequest;
import com.example.android.yourcartdelivery.Models.VendorDetailResponse;
import com.example.android.yourcartdelivery.Models.VendorModel;
import com.example.android.yourcartdelivery.Models.orderHistoryRequestModel;
import com.example.android.yourcartdelivery.Models.OrderHistoryResponse;
import com.example.android.yourcartdelivery.Models.LoginRequest;
import com.example.android.yourcartdelivery.Models.LoginResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

   // @FormUrlEncoded
    @POST("delivery/check/")
    Call<LoginResponse> getLogin(@Body LoginRequest loginRequest);

   @POST("delivery/deliveryorderhistory/")
    Call<OrderHistoryResponse> getData(@Body orderHistoryRequestModel id);

    @FormUrlEncoded
    @POST("delivery/reached_vendor")
    Call<Boolean> reachedVendor(@Field("order_id") String orderId
                                , @Field("vendor_phone") String vendorName
                                , @Field("delboy_phone") String delBoyPhone);

    @FormUrlEncoded
    @POST("delivery/getproductdetails")
    Call<ArrayList<ItemModel>> otpVerification(@Field("order_id") String orderId
            , @Field("otp") String otp
            , @Field("order_type") String orderType);


    @POST("delivery/vendor_details/")
    Call<VendorDetailResponse> getVendorDetails(@Body VendorDetailRequest vendorDetailRequest);

    @GET("delivery/get_checkpoint_details/")
    Call<CheckPointResponse> getCheckpointDetails(@Body JSONObject checkpointRequest);

    @GET("delivery/get_customer_details/")
    Call<CustomerModel> getCustomerDetails(@Query("body") JSONObject customerRequest);





}
