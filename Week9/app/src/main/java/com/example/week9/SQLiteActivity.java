package com.example.week9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLiteActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<Work> workArray;

    WorkAdapter adapter;

    Database database;
    String sql = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        toolbar = findViewById(R.id.toolbar_sqlite);
        listView = findViewById(R.id.listView);

        toolbar.setTitle("SQLite");
        setSupportActionBar(toolbar);

        workArray = new ArrayList<>();

        adapter = new WorkAdapter(this, R.layout.list_item, workArray);
        listView.setAdapter(adapter);

        // Tạo database Note
        database = new Database(this, "note.sqlite", null, 1);

        // Tạo bảng "Work"
        sql = "CREATE TABLE IF NOT EXISTS Work(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(2000))";
        database.QueryData(sql);

        // Insert data
//        sql = "INSERT INTO Work VALUES(null, 'Do Homework')";
//        database.QueryData(sql);

        // Select data
        GetWorkData();
    }

    private void GetWorkData() {
        // Select data
        sql = " SELECT * FROM Work";
        Cursor data = database.GetData(sql);
        workArray.clear();
        while (data.moveToNext()) {
            int id = data.getInt(0);
            String name = data.getString(1);
            workArray.add(new Work(id, name));
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_add) {
            DialogTheme();
        }

        return super.onOptionsItemSelected(item);
    }

    // Hiện ra cửa sổ nhỏ khi click vào Add
    private void DialogTheme() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_work);

        EditText edtAddWork = dialog.findViewById(R.id.editText_addWork);
        Button btnAdd = dialog.findViewById(R.id.button_add);
        Button btnCancel = dialog.findViewById(R.id.button_exit);

        btnAdd.setOnClickListener(view -> {
            String name = edtAddWork.getText().toString().trim();
            if (name.equals("")) {
                Toast.makeText(SQLiteActivity.this,
                        "Please type something", Toast.LENGTH_LONG).show();
            } else {
                sql = "INSERT INTO Work VALUES(null, '"+ name +"')";
                database.QueryData(sql);

                Toast.makeText(SQLiteActivity.this,
                        "Inserted successfully", Toast.LENGTH_LONG).show();
                dialog.dismiss();

                // Select data
                GetWorkData();
            }
        });

        btnCancel.setOnClickListener(view -> {
            dialog.dismiss();
        });

        dialog.show();
    }
}