package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_screen);
        findViewById(R.id.logo).startAnimation(AnimationUtils.loadAnimation(LoadingActivity.this,R.anim.anim_alpha));

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(LoadingActivity.this, SigninRegActivity.class));
            finish();
        },3000);
    }
}