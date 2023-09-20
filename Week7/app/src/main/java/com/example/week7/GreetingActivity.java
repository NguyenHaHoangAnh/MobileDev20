package com.example.week7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GreetingActivity extends AppCompatActivity {
    String fullName;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        Intent intent = getIntent();
        fullName = intent.getStringExtra("fullName");
        message = intent.getStringExtra("message");

        TextView textViewMessage = findViewById(R.id.textView_message);
        textViewMessage.setText(message);

        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }

    public void goBack() {
        Intent intent = new Intent();
        String feedback = "OK, Hello " + fullName + ". How are you?";
        intent.putExtra("feedback", feedback);

        setResult(RESULT_OK, intent);

        finish();
    }
}