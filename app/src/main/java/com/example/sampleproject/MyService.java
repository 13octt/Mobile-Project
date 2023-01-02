package com.example.sampleproject;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyService extends Application  {
    public static final String CHANNEL_ID = "channel_service";
    private MyReceiver mBroadcast = new MyReceiver();

    @Override
    public void onCreate() {
        super.onCreate();
        createChannelNotification();
    }
    public void createChannelNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel Service",importance);
            NotificationManager manager = getSystemService(NotificationManager.class);
//            if(manager != null)
//            manager.createNotificationChannel(channel);
        }
    }
}
