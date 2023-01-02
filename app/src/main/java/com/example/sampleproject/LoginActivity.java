package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject.helper.DBUserHelper;

public class LoginActivity extends AppCompatActivity {

    DBUserHelper dbHelper;
    TextView username;
    TextView password;
    TextView noAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (TextView) findViewById(R.id.username_et);
        password = (TextView) findViewById(R.id.password_et);
        noAccount = (TextView) findViewById(R.id.txt_no_account);

        password.setTransformationMethod(new PasswordTransformationMethod());
        dbHelper = new DBUserHelper(this);

        Button loginBtn = (Button) findViewById(R.id.btn_continue);

        loginBtn.setOnClickListener(view -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();
            if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                Toast.makeText(LoginActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkUserAndPass = dbHelper.checkUserPass(user, pass);
                if (checkUserAndPass) {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MapActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        noAccount.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

        });
    }
}

