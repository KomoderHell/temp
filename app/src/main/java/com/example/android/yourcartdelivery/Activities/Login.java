package com.example.android.yourcartdelivery.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.yourcartdelivery.Models.LoginRequest;
import com.example.android.yourcartdelivery.Models.LoginResponse;
import com.example.android.yourcartdelivery.R;
import com.example.android.yourcartdelivery.Utility.ApiInterface;
import com.example.android.yourcartdelivery.Utility.ApiUtils;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

   private Bundle notificationData;
    private EditText vendorId;
    private Button submitId;
    private String id;
    final Context context = this;
//    private FirebaseAuth mAuth;
    private String verificationid;
    private ApiInterface mAPIService;
    EditText otpView;
    private final String PREFERENCE_FILE_KEY = "myAppPreference";
    private SharedPreferences.Editor editor;
    private static final String KEY_USERNAME = "VendorName";
    private static final String KEY_USERID = "VendorId";
    private static final String KEY_PHONE = "VendorPhone";
    private SharedPreferences sharedPref;
    private String defaultValueName = "NoNAME";
    private String defaultValueId = "NoID";
    private String defaultValuePhone = "NoPhone";
    private String[] oldUserName;
    private String[] oldUserId;
    private String[] oldUserPhone;
    private String vendorPhone;
    private String vendorIdentity;
    private String vendorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         notificationData = getIntent().getExtras();

//        mAuth = FirebaseAuth.getInstance();
        mAPIService = ApiUtils.getAPIService();

        sharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        oldUserName = new String[]{sharedPref.getString(KEY_USERNAME, defaultValueName)};
        oldUserId = new String[]{sharedPref.getString(KEY_USERID, defaultValueId)};
        oldUserPhone = new String[]{sharedPref.getString(KEY_PHONE, defaultValuePhone)};
        vendorId = (EditText) findViewById(R.id.vendorID);
        submitId = (Button) findViewById(R.id.submitID);

        Toast.makeText(Login.this,"olduserId" + oldUserId[0], Toast.LENGTH_LONG).show();

        if(oldUserId[0].equals("NoID")) {

            submitId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    id = vendorId.getText().toString().trim();

                    LoginRequest request = new LoginRequest(id);
//                editor.putString(KEY_USERNAME, id);
//                editor.commit();

                    Call<LoginResponse> calldash = mAPIService.getLogin(request);
                    Toast.makeText(context, calldash.request().url().toString(), Toast.LENGTH_SHORT).show();
                    calldash.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, final Response<LoginResponse> response) {
                            Log.d("response.isSuccessful",""+response.isSuccessful());
                            if (response.isSuccessful()) {
                                if (response.body().getFound().equals("false")) {
                                    Toast.makeText(Login.this, "Wrong Credentials ", Toast.LENGTH_LONG).show();
                                } else {
//                                Toast.makeText(Login.this, response.body().getDel_boy_phone(), Toast.LENGTH_SHORT).show();
                                    Log.d("PHONE NUMBER", response.body().getDel_boy_phone());
                                    Log.d("Name", response.body().getDel_boy_name());
                                    Log.d("id", response.body().getDel_boy_id());
                                    vendorPhone = response.body().getDel_boy_phone();
                                    vendorName = response.body().getDel_boy_name();
                                    vendorIdentity = response.body().getDel_boy_id();
                                    //sendVerificationCode("+91" + response.body().getDel_boy_phone());
                                    final Dialog dialog = new Dialog(context);
                                    dialog.setContentView(R.layout.customdialogotp);
                                    dialog.show();

                                    otpView = dialog.findViewById(R.id.otp_view);


                                    Button btnSave = (Button) dialog.findViewById(R.id.otpget);

                                    btnSave.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            String code = otpView.getText().toString();
                                            Toast.makeText(Login.this, code, Toast.LENGTH_SHORT).show();
//                                            verifyCode(code);
                                            dialog.dismiss();

                                            oldUserId[0] = id;
                                            oldUserPhone[0] = "+91" + response.body().getDel_boy_phone();


                                            editor.putString(KEY_USERNAME, vendorName);
                                            editor.putString(KEY_USERID, vendorIdentity);
                                            editor.putString(KEY_PHONE, vendorPhone);
                                            editor.commit();
                                            loginIntoAccount();
                                        }
                                    });

                                }
                            } else {
                                Toast.makeText(Login.this,""+response, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Log.d("Failure ",t.getMessage());
                            Toast.makeText(Login.this, "Failure " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            });
        }
        else{
            vendorName = oldUserName[0];
            vendorIdentity = oldUserId[0];
            vendorPhone = oldUserPhone[0];
            Toast.makeText(Login.this, vendorName, Toast.LENGTH_SHORT).show();
            loginIntoAccount();
        }

    }

    private void verifyCode(String code){
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationid, code);
//        signInWithCredential(credential);
    }

    private void loginIntoAccount(){
//        Toast.makeText(Login.this, "Logging In", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.putExtra("notificationData",notificationData);
        Log.d("LoginNotificationData",""+notificationData);
        intent.putExtra("vendorName", vendorName);
        intent.putExtra("vendorPhone", vendorPhone);
        intent.putExtra("vendorIdentity", vendorIdentity);
        intent.putExtra("fromlogout", "true");
        startActivity(intent);
    }


//    private void signInWithCredential(PhoneAuthCredential credential) {
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Intent intent = new Intent(Login.this, Start.class);
//                            startActivity(intent);
//                            Toast.makeText(Login.this, "Signing In", Toast.LENGTH_LONG).show();

//                        } else {
//                            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }

//                });
//    }

    private void sendVerificationCode(String number){

//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                number,
//                60,
//                TimeUnit.SECONDS,
//                TaskExecutors.MAIN_THREAD,
//                mCallBack
//        );
    }

//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
//            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

//        @Override
//        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//            super.onCodeSent(s, forceResendingToken);
//            verificationid = s;
//            Toast.makeText(Login.this, "onCodeSent",Toast.LENGTH_LONG).show();
//        }

//       @Override
//        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//
//            String code = phoneAuthCredential.getSmsCode();
//            Toast.makeText(Login.this, "onVerificationCompleted" +  code,Toast.LENGTH_LONG).show();
//            if (code != null){
//                otpView.setText(code);
//            }
//        }

//        @Override
//        public void onVerificationFailed(FirebaseException e) {
//            Toast.makeText(Login.this, "onVerificationFailed " +  e.getMessage(),Toast.LENGTH_LONG).show();

     //   }
   // };
}
