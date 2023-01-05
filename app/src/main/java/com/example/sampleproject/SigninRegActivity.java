package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SigninRegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_and_reg);

        Button btnSignin = findViewById(R.id.signinbtn);
        btnSignin.setOnClickListener(view -> {
            Intent signin = new Intent(SigninRegActivity.this, LoginActivity.class);
            startActivity(signin);
        });

        Button btnRegister =  findViewById(R.id.registerbtn);
        btnRegister.setOnClickListener(view -> {
            Intent register = new Intent(SigninRegActivity.this, RegisterActivity.class);
            startActivity(register);
        });
    }
}