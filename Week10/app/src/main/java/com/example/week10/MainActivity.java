package com.example.week10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String AUTHORITY = "com.android.example.week10.AndroidIDProvider";
    static final String CONTENT_PATH = "backupdata";
    static final String URL = "content://" + AUTHORITY + "/" + CONTENT_PATH;
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String ID = "id";
    static final String VALUE = "value";

    Toolbar toolbar;
    EditText editText;
    Button button, button2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        editText = findViewById(R.id.edt_android_id);
        button = findViewById(R.id.btn_add);
        button2 = findViewById(R.id.btn_get);
        textView = findViewById(R.id.textView);


        setSupportActionBar(toolbar);
    }

    public void addData(View view) {
        // class to add values in the database
        ContentValues values = new ContentValues();

        // fetching text from user
        values.put(AndroidIDProvider.name, (editText.getText().toString()));

        // inserting into database through content URI
        getContentResolver().insert(AndroidIDProvider.CONTENT_URI, values);

        // displaying a toast message
        Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
    }

    @SuppressLint("Range")
    public void getData(View view) {
        // inserting complete table details in this text field
        TextView resultView= (TextView) findViewById(R.id.textView);

        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.week10/users"), null, null, null, null);

        // iteration of the cursor
        // to print whole table
        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
            resultView.setText(strBuild);
        }
        else {
            resultView.setText("No Records Found");
        }
    }
}