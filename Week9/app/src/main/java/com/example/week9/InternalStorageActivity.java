package com.example.week9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InternalStorageActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText edtMessage;
    Button btnRead, btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        toolbar = findViewById(R.id.toolbar_internalStorage);
        edtMessage = findViewById(R.id.editText_internalStorageMessage);
        btnRead = findViewById(R.id.button_internalStorageRead);
        btnWrite = findViewById(R.id.button_internalStorageWrite);

        toolbar.setTitle("Internal Storage");
        setSupportActionBar(toolbar);

        // Ghi dữ liệu
        btnWrite.setOnClickListener(view -> {
            // Thêm dữ liệu vào file
            try {
                // Mở file
                FileOutputStream fos = openFileOutput("mytextfile.txt", MODE_PRIVATE);
                // Ghi dữ liệu vào file
                String message = edtMessage.getText().toString().trim();
                if (message.equals("")) {
                    Toast.makeText(InternalStorageActivity.this,
                            "Please write something...", Toast.LENGTH_LONG).show();
                } else {
                    fos.write(message.getBytes());
                }
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Đặt edtMessage về "" và hiện thông báo đã lưu
            edtMessage.setText("");
            Toast.makeText(InternalStorageActivity.this,
                    "Save", Toast.LENGTH_LONG).show();
        });

        // Đọc dữ liệu
        btnRead.setOnClickListener(view -> {
            String data = "";
            try {
                // Đọc file
                FileInputStream fis = openFileInput("mytextfile.txt");
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

                String line;
                // Đọc từng dòng
                while ((line = br.readLine()) != null) {
                    data += line;
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Đọc dữ liệu rồi gán vào edtMessage và hiện thông báo đã đọc
            edtMessage.setText(data);
            Toast.makeText(InternalStorageActivity.this,
                    "Read", Toast.LENGTH_LONG).show();
        });
    }
}