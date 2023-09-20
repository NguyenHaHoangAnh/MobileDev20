package com.example.week6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tool bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Context menu - Menu ngữ cảnh
        Button button = findViewById(R.id.button);
        this.registerForContextMenu(button);

        // Popup menu
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1Clicked();
            }

            // Handle Popup menu
            private void button1Clicked() {
                // When click on Button 1, create Popup menu
                // anchor on Button 2
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,button2);

                // Inflate Popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                // Menu item click event
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

                        return true;
                    }
                });

                // Show Popup menu
                popupMenu.show();
            }
        });
    }

    // Handle Options menu
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

        return super.onOptionsItemSelected(item);
    }

    // Handle Context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        menu.setHeaderTitle("Context Menu");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

        return super.onContextItemSelected(item);
    }
}