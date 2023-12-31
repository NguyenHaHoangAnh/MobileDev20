package com.example.week14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.web);

        webView.loadUrl("https://www.google.com");

        // Enable JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        // Allow to handle onPageFinished and override Url loading
        webView.setWebViewClient(new WebViewClient());
    }
}