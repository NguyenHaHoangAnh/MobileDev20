package com.example.week9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreferencesActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText editTextUsername, editTextPassword;
    CheckBox checkBoxRemember;
    Button buttonLogin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        toolbar = findViewById(R.id.toolbar_sharedPreferences);
        editTextUsername = findViewById(R.id.editText_username);
        editTextPassword = findViewById(R.id.editText_password);
        checkBoxRemember = findViewById(R.id.checkBox_remember);
        buttonLogin = findViewById(R.id.button_login);

        toolbar.setTitle("Shared Preferences");
        setSupportActionBar(toolbar);

        // Tạo file để lưu dữ liệu: getSharedPreferences(name, mode)
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        // Lấy giá trị
        // getString(key, default value)
        editTextUsername.setText(sharedPreferences.getString("username", ""));
        editTextPassword.setText(sharedPreferences.getString("password", ""));
        checkBoxRemember.setChecked(sharedPreferences.getBoolean("checkBox", false));

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (username.equals("anh") && password.equals("123")) {
                    Toast.makeText(SharedPreferencesActivity.this, "Login successfully", Toast.LENGTH_LONG).show();
                    // Nếu check box có check
                    if (checkBoxRemember.isChecked()) {
                        // Mở file để edit file
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.putBoolean("checkBox", true);
                        // Xác nhận chỉnh sửa
                        editor.commit();
                    } else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("username");
                        editor.remove("password");
                        editor.remove("checkBox");

                        editor.commit();
                    }
                } else {
                    Toast.makeText(SharedPreferencesActivity.this,
                            "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}