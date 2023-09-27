package com.example.week9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExternalStorageActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText edtMessage;
    Button btnRead, btnWrite;

    private String filepath = "MyFileStorage";
    private String filename = "SampleFile.txt";
    File myExternalFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        toolbar = findViewById(R.id.toolbar_externalStorage);
        edtMessage = findViewById(R.id.editText_externalStorageMessage);
        btnRead = findViewById(R.id.button_externalStorageRead);
        btnWrite = findViewById(R.id.button_externalStorageWrite);

        toolbar.setTitle("External Storage");
        setSupportActionBar(toolbar);

        // check if external storage is available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            btnWrite.setEnabled(false);
        } else {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
        }

        btnWrite.setOnClickListener(view -> {
            try {
                FileOutputStream fos = new FileOutputStream(myExternalFile);
                String message = edtMessage.getText().toString().trim();
                if (message.equals("")) {
                    Toast.makeText(ExternalStorageActivity.this,
                            "Please write something...", Toast.LENGTH_LONG).show();
                } else {
                    fos.write(message.getBytes());
                }
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            edtMessage.setText("");
            Toast.makeText(ExternalStorageActivity.this,
                    "Save", Toast.LENGTH_LONG).show();
        });

        btnRead.setOnClickListener(view -> {
            String myData = "";
            try {
                FileInputStream fis = new FileInputStream(myExternalFile);
                DataInputStream in = new DataInputStream(fis);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = br.readLine()) != null) {
                    myData += line;
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            edtMessage.setText(myData);
            Toast.makeText(ExternalStorageActivity.this,
                    "Read", Toast.LENGTH_LONG).show();
        });
    }

    // Kiểm tra xem bộ nhớ ngoài SD card có read only ko
    // vì nếu read only thì ko thể tạo file trên đó đc
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState);
    }

    // Kiểm tra xem device có bộ nhớ ngoài ko
    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState);
    }
}