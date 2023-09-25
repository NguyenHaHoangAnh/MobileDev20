package com.example.week4;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ListViewActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    // Array of strings
    String[] items = {"Android", "Iphone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Mac OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        toolbar = findViewById(R.id.toolbar_listView);
        toolbar.setTitle("List View");
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.mobile_list);

        // ArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects)
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_view_items, R.id.item, items);
        listView.setAdapter(adapter);
    }
}