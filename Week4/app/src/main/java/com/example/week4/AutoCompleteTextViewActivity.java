package com.example.week4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class AutoCompleteTextViewActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView selection;
    AutoCompleteTextView autoCompleteTextView;
    // Array of strings
    String[] items = {"Android", "Iphone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Mac OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);

        toolbar = findViewById(R.id.toolbar_autoCompleteTextView);
        toolbar.setTitle("Auto Complete Text View");
        setSupportActionBar(toolbar);

        selection = findViewById(R.id.selection_autoCompleteTextView);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_view_items, R.id.item, items);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                selection.setText(autoCompleteTextView.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}