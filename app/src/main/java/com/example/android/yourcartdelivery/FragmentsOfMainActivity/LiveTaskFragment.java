package com.example.android.yourcartdelivery.FragmentsOfMainActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.yourcartdelivery.Adapters.Adapter_ordersLiveTask;
import com.example.android.yourcartdelivery.Models.ModelCellsLiveTask;
import com.example.android.yourcartdelivery.Models.ModelOrdersLiveTask;
import com.example.android.yourcartdelivery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.RemoteMessage;
import com.pusher.pushnotifications.PushNotificationReceivedListener;
import com.pusher.pushnotifications.PushNotifications;
import com.pusher.pushnotifications.auth.AuthData;
import com.pusher.pushnotifications.auth.AuthDataGetter;
import com.pusher.pushnotifications.auth.BeamsTokenProvider;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LiveTaskFragment extends Fragment {
    String TAG = "LiveTasks";
    String INSTANCE_KEY = "b0f7aac1-2560-466f-a93d-aa258d520a5a";
    RecyclerView recyclerView;
    private static ArrayList<ModelOrdersLiveTask> list = new ArrayList<>();
    Adapter_ordersLiveTask adapter_ordersLiveTask;
    String userId;
    String delBoyPhone;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.live_task_fragment,container,false);
        getActivity().setTitle("Live Tasks");
        v=view;
        recyclerView = view.findViewById(R.id.recycler_view_orders);
        setRecyclerView();

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Task : ", "getInstanceId failed", task.getException());
                            return;
                        }
                        String token = task.getResult().getToken();
                        Log.d("InstanceId(Token) : ",token);
                    }
                });

        Bundle bundle = getArguments();
        Bundle notificationData = bundle.getBundle("notificationData");
        JSONObject jsnobject = null;
        try {
            jsnobject = new JSONObject(String.valueOf(notificationData));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(jsnobject!=null){
            Log.d(TAG,""+notificationData+"*");
            String jsonStr = jsnobject.toString();
            Log.d("Json_object",jsonStr);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(jsnobject.getString("cell"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("Json_array_length",""+jsonArray.length());
            ModelOrdersLiveTask order = new ModelOrdersLiveTask();
            ArrayList<ModelCellsLiveTask> cellsList = new ArrayList<>();
            ModelCellsLiveTask cell = new ModelCellsLiveTask();

            //loop to get all json objects from data json array
            JSONObject jObj = null;
            for(int i=0; i<jsonArray.length(); i++)
            {
                try {
                    jObj = jsonArray.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    Log.d(TAG,"lat : "+jObj.get("lat")+"  long  : "+jObj.get("delivery_long") + "  order_id   :  "+jObj.get("order_id")+" -- ");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    cell.setDelivery_lat(""+jObj.get("lat"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    cell.setDelivery_long(""+jObj.get("delivery_long"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    order.setOrder_id(""+jObj.get("order_id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                cellsList.add(cell);
                Log.d("runTimes","1");
            }
            order.setCells(cellsList);
            addtolist(order);
        }
        Bundle LoginData = bundle.getBundle("LoginData");
        String text = null;
        if(LoginData!=null) {
            text = LoginData.getString("text");
            userId = LoginData.getString("userId");
            delBoyPhone = LoginData.getString("delBoyPhone");
        }
        Log.d("output",""+text+userId+delBoyPhone);
        final TextView textView = view.findViewById(R.id.textView);
        textView.setText(text);
        Button button = view.findViewById(R.id.buttonTemp);


       // PushNotifications.start(getContext().getApplicationContext(), INSTANCE_KEY);


       // BeamsTokenProvider tokenProvider = new BeamsTokenProvider(
       //         "http://gocoding.azurewebsites.net/delivery/pusher/beams-auth/",
       //         new AuthDataGetter() {
       //             @Override
       //             public AuthData getAuthData() {
       //                 // Headers and URL query params your auth endpoint needs to
       //                 // request a Beams Token for a given user
       //                 HashMap<String, String> headers = new HashMap<>();
                         //headers.put("del_boy_phone",delBoyPhone);
       //                 HashMap<String, String> queryParams = new HashMap<>();
       //                 queryParams.put("phone_no", delBoyPhone);
       //                 return new AuthData(
       //                         headers,
       //                         queryParams
        //                );
        //            }
        //        }
        //);


//        PushNotifications.setUserId(userId, tokenProvider, new BeamsCallback<Void, PusherCallbackError>() {
//            @Override
//            public void onSuccess(@NotNull Void... values) {
//                Toast.makeText(getContext(), "authentication", Toast.LENGTH_SHORT).show();
//                textView.append(" \n authenticated");
//            }
//
//            @Override
//            public void onFailure(PusherCallbackError error) {
//                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
//                textView.append(" \n error");
//            }
//        });




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrder();
            }
        });

        return view;
    }

    public void addOrder(){
        ModelOrdersLiveTask order = new ModelOrdersLiveTask();
        order.setOrder_id("12345678");
        ArrayList<ModelCellsLiveTask> s = new ArrayList<>();
        ModelCellsLiveTask cell = new ModelCellsLiveTask();
        for(int j=0; j<4 ; j++){
            cell.setDelivery_lat("25.4981");
            cell.setDelivery_long("1.2348");
            s.add(cell);
        }
        order.setCells(s);
        list.add(order);
        setRecyclerView();
        adapter_ordersLiveTask.notifyDataSetChanged();
    }

    public void removeFromList(int index){
        list.remove(index);
        setRecyclerView();
        adapter_ordersLiveTask.notifyDataSetChanged();
    }

    public void setRecyclerView(){
        recyclerView = v.findViewById(R.id.recycler_view_orders);
        adapter_ordersLiveTask = new Adapter_ordersLiveTask(getContext(),list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter_ordersLiveTask);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void addtolist(ModelOrdersLiveTask order) {
        list.add(order);
        adapter_ordersLiveTask.notifyDataSetChanged();
    }
}
