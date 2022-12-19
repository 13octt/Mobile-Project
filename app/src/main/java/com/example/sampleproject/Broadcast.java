package com.example.sampleproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if("com.example.sampleproject.MY_BC".equals(intent.getAction())) {
            String actionString = intent.getAction();
            Log.d(Broadcast.class.getSimpleName(), "My BroadcastReceiver");
            Toast.makeText(context, actionString, Toast.LENGTH_SHORT).show();
        }    }
}