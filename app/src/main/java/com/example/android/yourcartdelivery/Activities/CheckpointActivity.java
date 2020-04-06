package com.example.android.yourcartdelivery.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.yourcartdelivery.Adapters.DeliveryAdapter;
import com.example.android.yourcartdelivery.Models.CheckPointResponse;
import com.example.android.yourcartdelivery.Models.DelBoyResponse;
import com.example.android.yourcartdelivery.Models.OrderModel;
import com.example.android.yourcartdelivery.R;
import com.example.android.yourcartdelivery.Utility.ApiInterface;
import com.example.android.yourcartdelivery.Utility.ApiUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckpointActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    private final String PREFERENCE_FILE_KEY = "myAppPreference";
    private static final String KEY_PHONE = "VendorPhone";
    SharedPreferences sharedPreferences;
    TextView textViewToCustomerInfo;
    Button buttonTrackAll;

    CheckPointResponse checkPoint;
    ArrayList<DelBoyResponse> list;
    RecyclerView recyclerView;
    DeliveryAdapter deliveryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkpoint);
        apiInterface = ApiUtils.getAPIService();
        sharedPreferences = getSharedPreferences(PREFERENCE_FILE_KEY,MODE_PRIVATE);
        buttonTrackAll = findViewById(R.id.buttonTrackDelivery);

        final JSONObject request = new JSONObject();
        try {
            request.put("order_id", OrderModel.order_id);
            request.put("phone_no",sharedPreferences.getString(KEY_PHONE,"00"));
//            request.put("order_id",OrderModel.order_type);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<CheckPointResponse> call = apiInterface.getCheckpointDetails(request);
        call.enqueue(new Callback<CheckPointResponse>() {
            @Override
            public void onResponse(Call<CheckPointResponse> call, Response<CheckPointResponse> response) {
                if (response.isSuccessful()){
                    checkPoint = response.body();
                }
            }

            @Override
            public void onFailure(Call<CheckPointResponse> call, Throwable t) {
                Toast.makeText(CheckpointActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        initialize();

        recyclerView = findViewById(R.id.recyclerViewDelivery);
        deliveryAdapter = new DeliveryAdapter(checkPoint.getDeliveryBoys(),this);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(deliveryAdapter);

        textViewToCustomerInfo = findViewById(R.id.textViewToCustomerInfo);
        textViewToCustomerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OrderModel.isPrimary.equals("true")){
                    startActivity(new Intent(CheckpointActivity.this,CustomerActivity.class));
                }
                else{
                    Toast.makeText(CheckpointActivity.this, "order is completed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initialize() {
        list = new ArrayList<>();
        list.add(new DelBoyResponse("modi ji","9630249471","true","0000"));
        list.add(new DelBoyResponse("amit shah","9926497529","true","0000"));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(CheckpointActivity.this,MainActivity.class));
                return true;
            case R.id.call_support:
                Toast.makeText(CheckpointActivity.this, "Call Support is Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.stop_duty:
                Toast.makeText(CheckpointActivity.this, "Stop Duty is Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_right, menu);
        return true;
    }
}
