package com.example.android.yourcart_delivery.YourCartDelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.yourcart_delivery.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmpId;
    private Button buttonSubmitEmpId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view binding
        editTextEmpId = findViewById(R.id.editTextEmpId);
        buttonSubmitEmpId = findViewById(R.id.buttonSubmit);

    }
}
