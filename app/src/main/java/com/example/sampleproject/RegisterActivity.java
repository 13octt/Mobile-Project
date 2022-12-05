package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, password, repassword;
    Button register;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = (EditText) findViewById(R.id.username_edittext);
        email = (EditText) findViewById(R.id.email_edittext);
        password = (EditText) findViewById(R.id.password_edittext);
        repassword = (EditText) findViewById(R.id.re_password_edittext);
        register = (Button) findViewById(R.id.btn_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailAddress = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String rePass = repassword.getText().toString().trim();
                final String userName = username.getText().toString().trim();
                if (TextUtils.isEmpty(emailAddress)) {
                    email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    email.setError("Password is required");
                    return;
                }
                if (TextUtils.isEmpty(rePass)) {
                    email.setError("Re-password is required");
                    return;
                }
                if (TextUtils.isEmpty(userName)) {
                    email.setError("User name is required");
                    return;
                }
            }
        });
    }
}