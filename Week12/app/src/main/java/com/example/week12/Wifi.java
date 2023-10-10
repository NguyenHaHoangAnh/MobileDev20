package com.example.week12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Wifi extends AppCompatActivity {

    private WifiManager wifiManager;

    Toolbar toolbar;
    Button buttonOn, buttonOff;
    TextView txtWifiStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        toolbar = findViewById(R.id.toolbar);
        buttonOn = findViewById(R.id.button_on);
        buttonOff = findViewById(R.id.button_off);
        txtWifiStatus = findViewById(R.id.txt_wifiStatus);

        toolbar.setTitle("Wifi");
        setSupportActionBar(toolbar);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

    }

    public void enableWifi(View view) {
//        wifiManager.setWifiEnabled(true); -> < Android 10
        Intent intent = new  Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
        startActivityForResult(intent, 0);
        txtWifiStatus.setText("Wifi: On");
    }

    public void disableWifi(View view) {
//        wifiManager.setWifiEnabled(false); < Android 10
        Intent intent = new  Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
        startActivityForResult(intent, 0);
        txtWifiStatus.setText("Wifi: Off");
    }
}