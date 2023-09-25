package com.example.week4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class GridViewActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView selection;
    GridView gridView;
    // Array of strings
    String[] items = {"Android", "Iphone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Mac OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        toolbar = findViewById(R.id.toolbar_gridView);
        toolbar.setTitle("Grid View");
        setSupportActionBar(toolbar);

        selection = findViewById(R.id.selection_gridView);
        gridView = findViewById(R.id.grid);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_view_items, R.id.item, items);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selection.setText(items[position]);
            }
        });
    }
}