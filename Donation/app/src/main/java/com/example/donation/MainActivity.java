package com.example.donation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tool bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Donation.1.5");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.inflateMenu(R.menu.toolbar_menu);

        // Number picker
        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(1000);
        numberPicker.setMinValue(0);
        numberPicker.setValue(999);
    }
}