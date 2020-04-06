package com.example.android.yourcartdelivery.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.yourcartdelivery.Adapters.TaskAdapter;
import com.example.android.yourcartdelivery.Models.CellModel;
import com.example.android.yourcartdelivery.Models.ItemModel;
import com.example.android.yourcartdelivery.Models.OrderModel;
import com.example.android.yourcartdelivery.Models.VendorDetailRequest;
import com.example.android.yourcartdelivery.Models.VendorDetailResponse;
import com.example.android.yourcartdelivery.Models.VendorModel;
import com.example.android.yourcartdelivery.R;
import com.example.android.yourcartdelivery.Utility.ApiInterface;
import com.example.android.yourcartdelivery.Utility.ApiUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HEAD;

public class TaskActivity extends AppCompatActivity{

    RecyclerView recyclerViewCell;
    TaskAdapter taskAdapter;
    OrderModel order;
    ConstraintLayout layoutOrderPicked,constraintLayoutTasks;
    TextView viewCheckpoint;
    ApiInterface apiInterface;
    private final String PREFERENCE_FILE_KEY = "myAppPreference";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String KEY_USERNAME = "VendorName";
    private static final String KEY_USERID = "VendorId";
    private static final String KEY_PHONE = "VendorPhone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Current Tasks");
        sharedPreferences = getSharedPreferences(PREFERENCE_FILE_KEY,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        apiInterface = ApiUtils.getAPIService();

        String[] a = {"tsxt564"};
        String[] b = {"26"};
        String[] c = {"78"};
        order = new OrderModel("f9160043-4cdb-4a3a-a14f-318af7ba60fb","S",a,b,c,"1");
        inisializeData();

        viewCheckpoint= findViewById(R.id.textViewToCheckpoint);
        layoutOrderPicked = findViewById(R.id.cellOrderPickedLayout);
        constraintLayoutTasks = findViewById(R.id.constraintLayoutTasks);
        recyclerViewCell = findViewById(R.id.recyclerViewCells);

        viewCheckpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (OrderModel.order_type.equals("1")){
                    startActivity(new Intent(TaskActivity.this,CustomerActivity.class));

                }
                else {
                    startActivity(new Intent(TaskActivity.this,CheckpointActivity.class));

                }
            }
        });

        taskAdapter = new TaskAdapter(order.getCells(),this);
        recyclerViewCell.hasFixedSize();
        recyclerViewCell.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCell.setAdapter(taskAdapter);

       checkForAllTaskCompleted();
        Intent intent = getIntent();
        if(intent.getStringExtra("isCellCompleted")!=null && intent.getStringExtra("isCellCompleted").equals("true")) {
            int cellCompletedPosition = intent.getIntExtra("CompletedCellPosition",1000);
            setCellCompleted(cellCompletedPosition);
        }
    }

    public void checkForAllTaskCompleted() {
        int check = 1;
        for(int i=0;i<order.getCells().size();i++){
            Log.d("passCell",""+i+order.getCells().get(i).isCompleted());
            if(!order.getCells().get(i).isCompleted())
                check = 0;
        }
        if(check==1) {
            constraintLayoutTasks.setAlpha((float) 0.4);
            layoutOrderPicked.setVisibility(View.VISIBLE);
            viewCheckpoint.setVisibility(View.VISIBLE);
        }
        viewCheckpoint.setVisibility(View.VISIBLE);

    }

    private void inisializeData()  {

      JSONObject vendorRequest = new JSONObject();
        try {
            vendorRequest.put("order_type",OrderModel.order_type);
            vendorRequest.put("order_id",OrderModel.order_id);
            vendorRequest.put("phone_no",sharedPreferences.getString(KEY_PHONE,"3"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VendorDetailRequest vendorDetailRequest = new VendorDetailRequest(OrderModel.order_type,OrderModel.order_id,sharedPreferences.getString(KEY_PHONE,"3"));
        Toast.makeText(this, vendorDetailRequest.toString(), Toast.LENGTH_SHORT).show();
        Call<VendorDetailResponse> call = apiInterface.getVendorDetails(vendorDetailRequest);
        Toast.makeText(this, call.request().toString(), Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<VendorDetailResponse>() {
                         @Override
                         public void onResponse(Call<VendorDetailResponse> call, Response<VendorDetailResponse> response) {

                             Toast.makeText(TaskActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                             if (response.isSuccessful()){
                                 for (VendorModel vendor :
                                         response.body().getDetails()) {
                                     for (int i = 0; i < order.getCells().size(); i++) {
                                         if (vendor.getVendor_cell_id().equals(order.getCells().get(i).getId())){
                                             order.getCells().get(i).addVendor(vendor);
                                         }
                                     }

                                     Toast.makeText(TaskActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();



                                 }
                             }
                             else {
                                 Toast.makeText(TaskActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<VendorDetailResponse> call, Throwable t) {
                             Toast.makeText(TaskActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                         }
                     });



     /**   ArrayList < ItemModel > itemModels = new ArrayList<>();
        itemModels.add(new ItemModel("1","pijja","2"));
        itemModels.add(new ItemModel("2","thanda samosa","3"));
        itemModels.add(new ItemModel("4","garam ice cream","5"));
        ArrayList<VendorModel> vendorModelArrayList = new ArrayList<>();


        vendorModelArrayList.add(new VendorModel("Pizza Palace", "21221212", "false","0","25.16564","74.5364650","25.6846465454","74.654635","1" ));
        vendorModelArrayList.add(new VendorModel("Burger Raja", "121212121", "false", "0","25.16564","74.5364650","25.6846465454","74.654635","1"));

        vendorModelArrayList.get(0).setProducts(itemModels);
        vendorModelArrayList.get(1).setProducts(itemModels);

        CellModel cellModel = new CellModel("213","25.165","74.55213", vendorModelArrayList);
        cellModel.setVendors(vendorModelArrayList);
        cellModel.setVendors(vendorModelArrayList);
       list.add(cellModel);
       list.add(cellModel);
        taskAdapter.notifyDataSetChanged();
      **/
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(TaskActivity.this,MainActivity.class));
                return true;
            case R.id.call_support:
                Toast.makeText(TaskActivity.this, "Call Support is Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.stop_duty:
                Toast.makeText(TaskActivity.this, "Stop Duty is Clicked", Toast.LENGTH_SHORT).show();
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

    public void setCellCompleted(int position) {
        order.getCells().get(position).setCompleted(true);
        Log.d("cellCompleted",""+position);
        taskAdapter.notifyDataSetChanged();
        checkForAllTaskCompleted();
    }
}
