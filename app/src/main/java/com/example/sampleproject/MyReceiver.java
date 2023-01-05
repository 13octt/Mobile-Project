package com.example.sampleproject;

import static android.content.ContentValues.TAG;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sampleproject.helper.DBGraphHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyReceiver extends BroadcastReceiver {
    public Double temp,humi,wind;
    String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
    DBGraphHelper dbGraphHelper ;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
            dbGraphHelper = new DBGraphHelper(context);
//        Toast.makeText(context, "actionString", Toast.LENGTH_SHORT).show();
            temp = intent.getDoubleExtra("temp",0.00);
            humi =  intent.getDoubleExtra("humi",0.00);
            wind = intent.getDoubleExtra("wind",0.00);

        String actionString = intent.getAction();

        dbGraphHelper.insertData(temp,humi,wind);
////        Toast.makeText(context, actionString, Toast.LENGTH_SHORT).show();
//            ;
//        Intent serviceIntent = new Intent(context, BackgroundService.class);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            context.startForegroundService(serviceIntent);
//        }
    }


}