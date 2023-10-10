package com.example.week8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button buttonSendBradCast;
    TextView txtMessage;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("test.Broadcast")) {
                String message = intent.getStringExtra("message");
                txtMessage.setText(message);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        buttonSendBradCast = findViewById(R.id.button_sendBroadCast);
        txtMessage = findViewById(R.id.textView_message);

        // Toolbar
        toolbar.setTitle("Broadcast Receiver");
        setSupportActionBar(toolbar);

        // Handle Send Broadcast button click
        buttonSendBradCast.setOnClickListener(view -> {
            clickSendBroadcast();
        });
    }

    private void clickSendBroadcast() {
        Intent intent = new Intent();
        intent.setAction("test.Broadcast");
        intent.putExtra("message", "Hello, this is a broadcast from MainActivity");

        // Gửi Intent broadcast
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("test.Broadcast");
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
        Log.d("Message", "Unregister");
    }
}