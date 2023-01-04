package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject.helper.DBUserHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, password, rePassword;
    TextView forgotPass, haveAccount;
    Button register;
    DBUserHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = (EditText) findViewById(R.id.username_edittext);
        email = (EditText) findViewById(R.id.email_edittext);
        password = (EditText) findViewById(R.id.password_edittext);
        rePassword = (EditText) findViewById(R.id.re_password_edittext);
        register = (Button) findViewById(R.id.btn_register);
        haveAccount = (TextView)findViewById(R.id.have_account);
        dbHelper = new DBUserHelper(this);

        register.setOnClickListener(view -> {

            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String repass = rePassword.getText().toString().trim();
            String mail = email.getText().toString().trim();

            if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                Toast.makeText(RegisterActivity.this, "All fields is required", Toast.LENGTH_SHORT).show();
            else {
                if(pass.equals(repass)){
                    Boolean checkUser = dbHelper.checkUserName(user);
                    if(!checkUser){
                        Boolean insert = dbHelper.insertData(user, pass);
                        if(insert){
                            Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Password Are Not Matching", Toast.LENGTH_SHORT).show();
                }
            }
            email.setError("Email is required");
        });


        haveAccount.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}