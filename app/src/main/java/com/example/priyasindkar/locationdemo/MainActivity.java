package com.example.priyasindkar.locationdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Double latitude = 0.0, longitude = 0.0;
    private static final int REQUEST_CODE = 1;
    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        gps = new GPSTracker(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getloc();
                progressDialog.dismiss();
            }
        }, 3000);

    }

    private void getloc() {
        gps = new GPSTracker(MainActivity.this);
        if(gps.canGetLocation()){
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            gps.showSettingsAlert();
        }
    }


}
