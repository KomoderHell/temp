package com.example.android.yourcartdelivery.FragmentsOfMainActivity.OrderHistoryFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.yourcartdelivery.Adapters.OrderHistoryAdapter;
import com.example.android.yourcartdelivery.Models.orderHistoryRequestModel;
import com.example.android.yourcartdelivery.Models.OrderHistoryResponse;
import com.example.android.yourcartdelivery.Models.orderHistoryModel;
import com.example.android.yourcartdelivery.R;
import com.example.android.yourcartdelivery.Utility.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrimaryFragment extends Fragment {
    private RecyclerView recyclerView;
    OrderHistoryAdapter adapter;
    ArrayList<orderHistoryModel> items;
    ApiInterface jsonPlaceHolderApi;
    private static String Base_Url = "https://gocoding.azurewebsites.net/delivery/deliveryorderhistory/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.primary_fragment, container, false);
        items = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new OrderHistoryAdapter(items, getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gocoding.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("id", 3);
//
//        Gson prettyGson = new Gson();
//        String outputreq = prettyGson.toJson(hashMap);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Base_Url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        Log.d("input", outputreq);
//
//        try {
//            HttpURLConnection httpcon = (HttpURLConnection) ((new URL(Base_Url).openConnection()));
//            httpcon.setDoOutput(true);
//            httpcon.setRequestProperty("Content-Type", "application/json");
//            httpcon.setRequestProperty("Accept", "application/json");
//            httpcon.setRequestMethod("POST");
//            httpcon.connect();
//
//            OutputStream os = httpcon.getOutputStream();
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//            writer.write(outputreq);
//            writer.close();
//            os.close();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream(), "UTF-8"));
//
//            String line = null;
//            StringBuilder sb = new StringBuilder();
//
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//
//            br.close();
//            Log.d("comingdata", sb.toString());
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            Log.d("MalformedURLException", e.getMessage());
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//            Log.d("ProtocolException", e.getMessage());
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.d("IOException", e.getMessage());
//
//        }


        jsonPlaceHolderApi = retrofit.create(ApiInterface.class);

        orderHistoryRequestModel orderHistoryRequestModel = new orderHistoryRequestModel("3");
        Call<OrderHistoryResponse> call = jsonPlaceHolderApi.getData(orderHistoryRequestModel);
        call.enqueue(new Callback<OrderHistoryResponse>() {
            @Override
            public void onResponse(Call<OrderHistoryResponse> call, Response<OrderHistoryResponse> response) {
                Log.d("entered","yes");
                if(response.isSuccessful()) {
                    //MainPogoClass data = response.body();
                    ArrayList<orderHistoryModel> list = response.body().getLocations();
                    orderHistoryModel item = new orderHistoryModel();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getPrimary().equals("P")) {
                            item.setOrder_id("" + list.get(i).getOrder_id());
                            item.setDate("" + list.get(i).getDate());
                            item.setTime("" + list.get(i).getTime());
                            items.add(item);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    Log.d("Check", response.body().getLocations().get(0).toString());
                    Toast.makeText(getContext(), "response.isSuccessfull() = " + response.isSuccessful(), Toast.LENGTH_SHORT).show();
                }

                Log.d("ResponeString : ",""+response.toString());
                Log.d("responseBody : ",""+response.body());
                Log.d("error : ",""+response.errorBody());
                //Toast.makeText(getContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                Log.d("IsSuccessfull : ",""+response.isSuccessful());
            }

            @Override
            public void onFailure(Call<OrderHistoryResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Failure  : ",t.getMessage());
            }
        });

        orderHistoryModel item = new orderHistoryModel();
        for (int i = 0; i < 2; i++) {
                item.setOrder_id("25465");
                item.setDate("20-Jan-2020");
                item.setTime("2:00 PM");
                items.add(item);
        }
        adapter.notifyDataSetChanged();

        return view;
    }

}
