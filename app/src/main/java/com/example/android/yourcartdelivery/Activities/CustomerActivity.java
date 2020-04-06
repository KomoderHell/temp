package com.example.android.yourcartdelivery.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.yourcartdelivery.Models.CustomerModel;
import com.example.android.yourcartdelivery.Models.OrderModel;
import com.example.android.yourcartdelivery.R;
import com.example.android.yourcartdelivery.Utility.ApiInterface;
import com.example.android.yourcartdelivery.Utility.ApiUtils;
import com.google.android.gms.common.api.Api;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private final String PREFERENCE_FILE_KEY = "myAppPreference";
    private static final String KEY_PHONE = "VendorPhone";

    ApiInterface apiInterface;

    //views
    TextView textViewPrice;
    TextView textViewAddress;
    TextView orderId;
    TextView textViewTotal;
    TextView textViewName;
    ImageView imageViewCall;
    ImageView imageViewTrack;
    TextView textViewIReached;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        sharedPreferences = getSharedPreferences(PREFERENCE_FILE_KEY,MODE_PRIVATE);
        apiInterface = ApiUtils.getAPIService();

        //view binding
        textViewAddress = findViewById(R.id.textViewAddress);
        textViewIReached = findViewById(R.id.textViewIReached);
        textViewName = findViewById(R.id.Customername);
        orderId = findViewById(R.id.order_id);
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewTotal = findViewById(R.id.totalAmmount);
        imageViewCall = findViewById(R.id.imageViewCall);
        imageViewTrack = findViewById(R.id.imageViewTrack);

        final JSONObject request = new JSONObject();
        try {
            request.put("order_id", OrderModel.order_id);
//            request.put("phone_id",sharedPreferences.getString(KEY_PHONE,"00"));
            request.put("order_type",OrderModel.order_type);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Call<CustomerModel> call = apiInterface.getCustomerDetails(request);
        call.enqueue(new Callback<CustomerModel>() {
            @Override
            public void onResponse(@NotNull Call<CustomerModel> call, @NotNull Response<CustomerModel> response) {
                Toast.makeText(CustomerActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    orderId.setText(OrderModel.getOrder_id());
                    textViewAddress.setText(response.body().getCust_address());
                    textViewName.setText(response.body().getFirst_name() +" " + response.body().getLast_name());

                }
            }

            @Override
            public void onFailure(Call<CustomerModel> call, Throwable t) {

            }
        });

    }
}
