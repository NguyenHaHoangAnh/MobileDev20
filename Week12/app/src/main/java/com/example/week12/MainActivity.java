package com.example.week12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button buttonSensors;
    Button buttonWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        buttonSensors = findViewById(R.id.button_sensors);
        buttonWifi = findViewById(R.id.button_wifi);

        toolbar.setTitle("Week 12");
        setSupportActionBar(toolbar);
    }

    public void goToSensors(View view) {
        Intent intent = new Intent(MainActivity.this, Sensors.class);
        startActivity(intent);
    }

    public void goToWifi(View view) {
        Intent intent = new Intent(MainActivity.this, Wifi.class);
        startActivity(intent);
    }
}