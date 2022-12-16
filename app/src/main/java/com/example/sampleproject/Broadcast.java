package com.example.sampleproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(".Insight".equals(intent.getAction()))
            Log.d(Broadcast.class.getSimpleName(), "My BroadcastReceiver");
        else
            Log.d(Broadcast.class.getSimpleName(), "huhu");
    }
}
