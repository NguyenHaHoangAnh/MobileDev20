package com.example.week7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static int MY_REQUEST_CODE = 1000;
    EditText editTextFullName;
    Button buttonSendMessage;
    TextView textViewFeedback;
    Button buttonImplicitIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Explicit Intent");
        setSupportActionBar(toolbar);

        editTextFullName = findViewById(R.id.editText_fullName);
        buttonSendMessage = findViewById(R.id.button_sendMessage);
        textViewFeedback = findViewById(R.id.textView_feedback);
        buttonImplicitIntent = findViewById(R.id.button_implicitIntent);

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        buttonImplicitIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToImplicitIntent();
            }
        });
    }

    // Phương thức được gọi khi click vào nút "Send Message to Greeting Activity"
    public void sendMessage() {
        String fullName = editTextFullName.getText().toString();
        String message = "Hello, Please say hello to me!";

        Intent intent = new Intent(MainActivity.this, GreetingActivity.class);
        intent.putExtra("fullName", fullName);
        intent.putExtra("message", message);

        // Khởi động Activity mà không cần feedback
        // this.startActivity(intent);

        // Khởi động Activity và lấy feedback
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    // When Greeting Activity is completed, it sends back a feedback
    // (If you started it by startActivityForResult())
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String feedback = data.getStringExtra("feedback");
            textViewFeedback.setText(feedback);
        } else {
            textViewFeedback.setText("!?");
        }
    }

    public void goToImplicitIntent() {
        String fullName = editTextFullName.getText().toString();

        Intent intent = new Intent(MainActivity.this, ImplicitIntentActivity.class);
        intent.putExtra("fullName", fullName);

        startActivity(intent);
    }
}