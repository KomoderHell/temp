package com.example.android.yourcartdelivery.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.android.yourcartdelivery.FragmentsOfMainActivity.EarningsFragment;
import com.example.android.yourcartdelivery.FragmentsOfMainActivity.LiveTaskFragment;
import com.example.android.yourcartdelivery.FragmentsOfMainActivity.NoticesFragment;
import com.example.android.yourcartdelivery.FragmentsOfMainActivity.OrderHistoryFragment.OrderHistoryFragment;
import com.example.android.yourcartdelivery.FragmentsOfMainActivity.ProfileFragment;
import com.example.android.yourcartdelivery.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    String text;
    String userId;
    String delBoyPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
        Intent intent = getIntent();
        Bundle bundle_notification = intent.getBundleExtra("notificationData");
        Log.d("bundle_notification",""+bundle_notification);
        bundle.putBundle("notificationData",bundle_notification);
        text = intent.getStringExtra("vendorName");
        text += " " + intent.getStringExtra("vendorPhone");
        text += " " + intent.getStringExtra("vendorIdentity");
            userId = intent.getStringExtra("vendorIdentity");
            delBoyPhone = intent.getStringExtra("vendorPhone");
            Log.d("input",""+text+userId+delBoyPhone);
        Bundle bundle_loginData = new Bundle();
            bundle_loginData.putString("text",text);
            bundle_loginData.putString("userId",userId);
            bundle_loginData.putString("delBoyPhone",delBoyPhone);
            bundle.putBundle("LoginData",bundle_loginData);
        LiveTaskFragment lv = new LiveTaskFragment();
        lv.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    lv).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Bundle bundle = new Bundle();
                bundle.putString("text",text);
                bundle.putString("userId",userId);
                bundle.putString("delBoyPhone",delBoyPhone);
                LiveTaskFragment lv = new LiveTaskFragment();
                lv.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        lv).commit();
                return true;
            case R.id.call_support:
                Toast.makeText(MainActivity.this, "Call Support is Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.stop_duty:
                Toast.makeText(MainActivity.this, "Stop Duty is Clicked", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Profile:
                StartFragment(new ProfileFragment());
                break;
            case R.id.Notices:
                StartFragment(new NoticesFragment());
                break;
            case R.id.Earning:
                StartFragment(new EarningsFragment());
                break;
            case R.id.OrderHistory:
                StartFragment(new OrderHistoryFragment());
                break;
            case R.id.Logout:
                Toast.makeText(MainActivity.this, "Logout is clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

        private void StartFragment (Fragment fragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    fragment).commit();
        }


        @Override
        public void onBackPressed () {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

}
