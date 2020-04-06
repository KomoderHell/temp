package com.example.android.yourcartdelivery.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.yourcartdelivery.Adapters.CellAdapter;
import com.example.android.yourcartdelivery.Models.VendorModel;
import com.example.android.yourcartdelivery.R;
import com.example.android.yourcartdelivery.Utility.ApiInterface;
import com.example.android.yourcartdelivery.Utility.ApiUtils;

import java.util.ArrayList;

public class CellActivity extends AppCompatActivity implements CellAdapter.onItemClickListener{

    ArrayList<VendorModel> list;
    RecyclerView recyclerView;
    CellAdapter cellAdapter;
    public static TextView textViewToNextCell;
    public ImageView image;
    int positionOfCell;
    ApiInterface apiInterface;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CellActivity.this,TaskActivity.class));
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell);
        image = findViewById(R.id.progress);
        apiInterface = ApiUtils.getAPIService();
        textViewToNextCell = findViewById(R.id.textViewToNextCell);
        final Intent intent = getIntent();
        positionOfCell = intent.getIntExtra("cellNo",0);
        setTitle("CELL "+positionOfCell);
        recyclerView = findViewById(R.id.recyclerViewVendors);
        list = (ArrayList<VendorModel>) intent.getSerializableExtra("list");

        cellAdapter = new CellAdapter(list,this);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cellAdapter);
        cellAdapter.setOnItemClickListener(CellActivity.this);

        findViewById(R.id.textViewToNextCell).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("textViewToNextCell","clicked");
                Intent intent = new Intent(CellActivity.this,TaskActivity.class);
                intent.putExtra("CompletedCellPosition",positionOfCell);
                intent.putExtra("isCellCompleted","true");
                startActivity(intent);
            }
        });
        checkForNextCell(list);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(CellActivity.this,MainActivity.class));
                return true;
            case R.id.call_support:
                Toast.makeText(CellActivity.this, "Call Support is Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.stop_duty:
                Toast.makeText(CellActivity.this, "Stop Duty is Clicked", Toast.LENGTH_SHORT).show();
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

    public void checkForNextCell(ArrayList<VendorModel> l){
        int check = 1;
        for(int i=0;i<l.size();i++){
            Log.d("pass",""+i+l.get(i).getIsPicked());
            if(!l.get(i).getIsPicked().equals("true"))
            check = 0;
        }
        if(check==1) {
            image.setVisibility(View.INVISIBLE);
            textViewToNextCell.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void orderPicked(int position ) {
        Log.d("orderPicked","run");
        list.get(position).setIsPicked("true");
        cellAdapter.notifyDataSetChanged();
       checkForNextCell(list);
    }
}
