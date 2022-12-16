package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class BroadcastActivity extends AppCompatActivity {
    private Broadcast mBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insight);
        mBroadcast = new Broadcast();
        Intent intent = new Intent("com.example.sampleproject.Broadcast");
        sendBroadcast(intent);

    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mBroadcast);
    }
    public void ReceiveData(){

    }
}