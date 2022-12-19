package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sampleproject.Model.Asset;
import com.example.sampleproject.Model.Attributes;
import com.example.sampleproject.Model.Main;
import com.example.sampleproject.Model.Sys;
import com.example.sampleproject.Model.Value;
import com.example.sampleproject.Model.Weather;
import com.example.sampleproject.Model.WeatherData;
import com.example.sampleproject.Model.Wind;
import com.example.sampleproject.helper.DBGraphHelper;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BroadcastActivity extends AppCompatActivity {
    private MyReceiver mBroadcast = new MyReceiver();
    String humi,wind,temp;
    APIInterface apiInterface;
    String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
    private List<String> data;
    DBGraphHelper dbGraphHelper;
    SQLiteDatabase sqLiteDatabase;
    private ArrayList<Main> mains;
    Cursor cursor;

    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[0]);
    LineGraphSeries<DataPoint> seriesTemp = new LineGraphSeries<>(new DataPoint[0]);
    LineGraphSeries<DataPoint> seriesPressure = new LineGraphSeries<>(new DataPoint[0]);

    //    BarGraphSeries<DataPoint> series;


    List<Double> humilist = new ArrayList<>();
    List<Double> templist = new ArrayList<>();
    List<Double> pressurelist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insight);
//        IntentFilter filter = new IntentFilter("com.example.sampleproject.MY_BC");
//        registerReceiver(mBroadcast,filter);
        Intent intentService = new Intent (this,BackgroundService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intentService);
        }
        foregroundServiceRunning();
        dbGraphHelper = new DBGraphHelper(this);
        sqLiteDatabase = dbGraphHelper.getWritableDatabase();

        GraphView graph = (GraphView) findViewById(R.id.graph);
        GraphView graphTemp = (GraphView)findViewById(R.id.graph2);
        GraphView graphPress = (GraphView)findViewById(R.id.graph3);
        cursor = dbGraphHelper.getData("SELECT * FROM graph");

        // Test graph
        String[] queryResult = new String[]{
                dbGraphHelper.KEY_HUMIDITY,
                dbGraphHelper.KEY_TEMP,
                dbGraphHelper.KEY_PRESSURE
        };

        while (cursor.moveToNext()) {
            String str = cursor.getString(0);
            String str1 = cursor.getString(1);
            String str2 = cursor.getString(2);
//            String str3 = cursor.getString(3);
            humilist.add(Double.parseDouble(str1));
            templist.add(Double.parseDouble(str));
            pressurelist.add(Double.parseDouble(str2));

//            Toast.makeText(this, str + " " + str1 + " " + str2, Toast.LENGTH_LONG).show();
    }
        dbGraphHelper = new DBGraphHelper(this);
        sqLiteDatabase = dbGraphHelper.getWritableDatabase();

        series.resetData(getDataPoint(humilist));
        seriesTemp.resetData(getDataPoint(templist));
        seriesPressure.resetData(getDataPoint(pressurelist));

        graph.addSeries(series);
        graphTemp.addSeries(seriesTemp);
        graphPress.addSeries(seriesPressure);

        graphCustom(graphTemp, "Temperature", seriesTemp);
        graphCustom(graph, "Humidity", series);
        graphCustom(graphPress, "Pressure", seriesPressure);
    }
    private void graphCustom(GraphView graph, String name, LineGraphSeries<DataPoint> series1) {
        series1.setTitle(name);
        series1.setColor(Color.BLACK);
//        GridLabelRenderer gridLR = graph.getGridLabelRenderer();
//        String donvi;
//        if(name == "Temperature")
//            donvi = "°C";
//        else
//            if(name == "Humidity")
//                donvi = "%";
//            else
//                donvi = "atm";
//        gridLR.setVerticalAxisTitle(donvi);
//        gridLR.setVerticalAxisTitleTextSize(70);

        series1.setThickness(10);
        graph.setTitleColor(Color.BLUE);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().scrollToEnd();
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
    }
    @Override
    protected void onStart() {
        super.onStart();
//        Intent intent = new Intent("com.example.sampleproject.MY_BC");
////            sendBroadcast(intent);
//        if(currentTime.equals("09:24") )
//        Toast.makeText(this, currentTime, Toast.LENGTH_SHORT).show();
//            data = getData();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            data.stream().collect(Collectors.summingDouble(string -> Double.parseDouble(string)));
//        }
//            for(String datas : data)   {
//            Log.e("data",datas);}
}

    /**
     * {@inheritDoc}
     * <p>
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(currentTime.equals("09:33") )
//            Toast.makeText(this, currentTime, Toast.LENGTH_SHORT).show();
//        unregisterReceiver(mBroadcast);
    }
//    public void ReceiveData(){
//
//    }
    public boolean foregroundServiceRunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo serviceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)){
            if(MyService.class.getName().equals(serviceInfo.service.getClassName())){
                return true;
            }
        }
        return false;
    }
    private DataPoint[] getDataPoint(List<Double> getdb) {
//        String[] columns = {"TIME", "TEMP"};
        DataPoint[] dp = new DataPoint[cursor.getCount()];
        //  if(cursor.getString(1)== "6H4PeKLRMea1L0WsRXXWp9") {
        for (int i = 0; i < cursor.getCount(); i++) {
//            cursor.moveToNext();
            dp[i] = new DataPoint(i*2, getdb.get(i));
        }
        //}
        return dp;
    }
    @SuppressLint("Range")
    private List<String> getData() {
        List<String> users = new ArrayList<>();

        cursor = dbGraphHelper.getColumnData("humidity");
        while (cursor.moveToNext()) {
            users.add(cursor.getString(Integer.parseInt(cursor.getString(cursor.getColumnIndex(dbGraphHelper.KEY_TEMP)))));
        }
        return users;
    }

}