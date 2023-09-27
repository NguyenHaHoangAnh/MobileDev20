package com.example.week9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button buttonSharedPreferences, buttonAndroidFileSystem,
            buttonInternalStorage, buttonExternalStorage,
            buttonSQLite, buttonNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        buttonSharedPreferences = findViewById(R.id.button_sharedPreferences);
        buttonAndroidFileSystem = findViewById(R.id.button_androidFileSystem);
        buttonInternalStorage = findViewById(R.id.button_internalStorage);
        buttonExternalStorage = findViewById(R.id.button_externalStorage);
        buttonSQLite = findViewById(R.id.button_SQLite);
        buttonNetwork = findViewById(R.id.button_network);

        toolbar.setTitle("Main");
        setSupportActionBar(toolbar);

        buttonSharedPreferences.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SharedPreferencesActivity.class);
            startActivity(intent);
        });

        buttonInternalStorage.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, InternalStorageActivity.class);
            startActivity(intent);
        });

        buttonExternalStorage.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ExternalStorageActivity.class);
            startActivity(intent);
        });

        buttonSQLite.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SQLiteActivity.class);
            startActivity(intent);
        });
    }
}