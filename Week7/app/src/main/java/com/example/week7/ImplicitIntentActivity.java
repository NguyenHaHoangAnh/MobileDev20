package com.example.week7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImplicitIntentActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageViewGoogleChrome, imageViewMessage,
            imageViewDial, imageViewImage, imageViewContact;
    Button buttonImplicitBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Implicit Intent");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String fullName = intent.getStringExtra("fullName");

        textView = findViewById(R.id.textView_welcome);
        textView.setText("Hello " + fullName + ", this is Implicit Intent Activity");

        buttonImplicitBack = findViewById(R.id.button_implicitBack);
        buttonImplicitBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToMainActivity();
            }
        });

        // Google Chrome
        imageViewGoogleChrome = findViewById(R.id.imageView_googleChrome);
        imageViewGoogleChrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGoogleChrome();
            }
        });

        // Message
        imageViewMessage = findViewById(R.id.imageView_message);
        imageViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMessage();
            }
        });

        // Call
        imageViewDial = findViewById(R.id.imageView_dial);
        imageViewDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDial();
            }
        });

        // Image
        imageViewImage = findViewById(R.id.imageView_image);
        imageViewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToImage();
            }
        });

        // Contact
        imageViewContact = findViewById(R.id.imageView_contact);
        imageViewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToContact();
            }
        });
    }

    public void goBackToMainActivity() {
        finish();
    }

    public void goToGoogleChrome() {
        Intent intent = new Intent();

        // Access a web url
//        String url = "https://google.com/";
        // Access a place in google map
//        String geoCode = "geo:0,0?q=1860+east+18th+street+cleveland+oh";
//
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(url)); // intent.setData(Uri.parse(geoCode));

//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Access google and search
        intent.setAction(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, "blackpink");

        startActivity(intent);
    }

    public void goToMessage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("sms:0123456789"));
        intent.putExtra("sms_body", "Hello...");

        startActivity(intent);
    }

    public void goToDial() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0123456789"));

        startActivity(intent);
    }

    public void goToImage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/picture/*");

        startActivity(intent);
    }

    public void goToContact() {
        String url = "content://contacts/people/";

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        startActivity(intent);
    }
}