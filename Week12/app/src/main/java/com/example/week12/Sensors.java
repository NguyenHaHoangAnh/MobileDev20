package com.example.week12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Sensors extends AppCompatActivity implements SensorEventListener {

    private static SensorManager sensorManager;
    private Sensor brightness;

    Toolbar toolbar;
    TextView txtLightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        toolbar = findViewById(R.id.toolbar);
        txtLightSensor = findViewById(R.id.txt_lightSensor);

        toolbar.setTitle("Sensors");
        setSupportActionBar(toolbar);

        // Set default night mode -> Make sure phone on light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Set up Sensor
        setUpSensor();
    }

    // Declare set up function
    private void setUpSensor() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        brightness = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    // These are 2 methods from sensorEventListener Interface
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float light = event.values[0];

            txtLightSensor.setText("Light Sensor: " + light + "\n" + brightness(light));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    // Function to show message according to the brightness
    public String brightness(float light) {
        int lightInt = (int) light;

        if (lightInt == 0) return "Pitch black";
        else if (lightInt >= 1 && lightInt <= 10) return "Dark";
        else if (lightInt >= 11 && lightInt <= 50) return "Grey";
        else if (lightInt >= 51 && lightInt <= 5000) return "Normal";
        else if (lightInt >= 5001 && lightInt <= 25000) return "Incredibly brightness";
        else return "This light will blind you";
    }

    // This is onResume function of app
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, brightness, SensorManager.SENSOR_DELAY_FASTEST);
    }

    // This is onPause function of app
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}