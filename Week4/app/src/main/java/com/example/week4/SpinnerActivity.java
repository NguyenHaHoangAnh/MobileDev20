package com.example.week4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SpinnerActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView selection;
    Spinner spinner;
    // Array of strings
    String[] items = {"Android", "Iphone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Mac OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        toolbar = findViewById(R.id.toolbar_spinner);
        toolbar.setTitle("Spinner");
        setSupportActionBar(toolbar);

        selection = findViewById(R.id.selection_spinner);
        spinner = findViewById(R.id.spinner);

        // ArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view_items, R.id.item, items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection.setText(items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}