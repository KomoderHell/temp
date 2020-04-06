package com.example.android.yourcartdelivery.Utility;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.android.yourcartdelivery.FragmentsOfMainActivity.LiveTaskFragment;
import com.example.android.yourcartdelivery.Activities.MainActivity;
import com.example.android.yourcartdelivery.Models.ModelCellsLiveTask;
import com.example.android.yourcartdelivery.Models.ModelOrdersLiveTask;
import com.example.android.yourcartdelivery.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    LiveTaskFragment lv = new LiveTaskFragment();

    @Override
    public void onNewToken(@NonNull String s) {
        //super.onNewToken(s);
        Log.d("New Token : ",s);
        Log.d("Messaging class: ","entered");
        String recentToken = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.FCM_TOKEN),s);
        Log.d("Token : ",s);
        editor.commit();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
        else{
            Log.d(TAG,"notification = null");
        }
        String title = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();
        Log.d(TAG,"title : " + title + "body : "+ message);

        Map<String,String> data = remoteMessage.getData();
        Toast.makeText(this,data.get("cell"), Toast.LENGTH_SHORT).show();
        Log.d("cell : ",data.get("cell"));
        JSONObject jsnobject = new JSONObject(data);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsnobject.getString("cell"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String jsonStr = jsnobject.toString();

        Log.e("Json_object",jsonStr);
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
        lv.addtolist(order);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channelId",
                    "Notification Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
