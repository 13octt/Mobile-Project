package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject.helper.DBUserHelper;

public class LoginActivity extends AppCompatActivity {

    DBUserHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView username = (TextView) findViewById(R.id.usernametext);
        TextView password = (TextView) findViewById(R.id.passwordtext);
        password.setTransformationMethod(new PasswordTransformationMethod());
        dbHelper = new DBUserHelper(this);

        Button loginbtn = (Button) findViewById(R.id.btn_continue);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserAndPass = dbHelper.checkUserPass(user, pass);
                    if (checkUserAndPass == true) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MapActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
//                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin"))
//                {
//                    Toast.makeText(LoginActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            startActivity(new Intent(LoginActivity.this, MapActivity.class));
//
//                            finish();
//                        }
//                    }, 1000);

            }
//                else
//                    Toast.makeText(LoginActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
//            }
//        });

        });
    }
}

