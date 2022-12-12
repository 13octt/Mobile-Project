package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sampleproject.helper.DBGraphHelper;

public class InsightActivity extends AppCompatActivity {

    // Code Insight
    DBGraphHelper dbGraphHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insight);

        dbGraphHelper = new DBGraphHelper(this);
    }
}