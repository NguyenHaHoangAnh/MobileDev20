package com.example.week8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button buttonSendBradCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        buttonSendBradCast = findViewById(R.id.button_sendBroadCast);

        // Toolbar
        toolbar.setTitle("Broadcast Receiver");
        setSupportActionBar(toolbar);

        // Handle Send Broadcast button click
//        buttonSendBradCast.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, TestBroadcastActivity.class);
//
//                startActivity(intent);
//            }
//        });

        Intent intent = new Intent();
        intent.setAction("test.Broadcast");
        intent.putExtra("message", "Hello, this is a broadcast from MainActivity");

        // Gửi Intent broadcast
        sendBroadcast(intent);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        // Đăng ký Broadcast Receiver để nhận Intent
        Broadcast receiver = new Broadcast();
        IntentFilter intentFilter = new IntentFilter("test.Broadcast");
        registerReceiver(receiver, intentFilter);
    }
}