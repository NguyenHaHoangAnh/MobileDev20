package com.example.week13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Week 13");
        setSupportActionBar(toolbar);
    }

    public void goToMediaPlayer(View view) {
        Intent intent = new Intent(MainActivity.this, MediaPlayerActivity.class);
        startActivity(intent);
    }

    public void goToLocation(View view) {
        Intent intent = new Intent(MainActivity.this, LocationActivity.class);
        startActivity(intent);
    }
}