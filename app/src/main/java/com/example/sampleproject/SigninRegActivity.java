package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SigninRegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_and_reg);

        Button btnSignin = (Button) findViewById(R.id.signinbtn);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signin = new Intent(SigninRegActivity.this, LoginActivity.class);
                startActivity(signin);
            }
        });

        Button btnRegister = (Button) findViewById(R.id.registerbtn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(SigninRegActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });
    }
}