package com.example.sampleproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.sampleproject.Model.Main;
import com.example.sampleproject.helper.DBGraphHelper;
import com.google.android.material.navigation.NavigationView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BroadcastActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private MyReceiver mBroadcast = new MyReceiver();
    String humi, wind, temp;
    APIInterface apiInterface;
    String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
    private List<String> data;
    DBGraphHelper dbGraphHelper;
    SQLiteDatabase sqLiteDatabase;
    private ArrayList<Main> mains;
    Cursor cursor;
    DrawerLayout drawerLayout;
    ImageView imgMenu;

    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[0]);
    BarGraphSeries<DataPoint> seriesTemp = new BarGraphSeries<>(new DataPoint[0]);
    LineGraphSeries<DataPoint> seriesPressure = new LineGraphSeries<>(new DataPoint[0]);

    //    BarGraphSeries<DataPoint> series;

    List<Double> humilist = new ArrayList<>();
    List<Double> templist = new ArrayList<>();
    List<Double> pressurelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insight);

        drawerLayout = findViewById(R.id.layout_insight);
        imgMenu = findViewById(R.id.ic_menu_insight);

        imgMenu.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
        NavigationView navigationView = findViewById(R.id.nav_view_insight);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.it_map:
                    Intent map = new Intent(BroadcastActivity.this, MapActivity.class);
                    startActivity(map);
                    break;
                case R.id.it_weather:
                    Intent weather = new Intent(BroadcastActivity.this, WeatherActivity.class);
                    startActivity(weather);
                    break;
                case R.id.it_user_role:
                    Intent userRoles = new Intent(BroadcastActivity.this, UserRolesActivity.class);
                    startActivity(userRoles);
                    break;
                case R.id.it_asset_descriptor:
                    Intent des = new Intent(BroadcastActivity.this, AssetDescriptorActivity.class);
                    startActivity(des);
                    break;
                case R.id.it_graph:
                    Intent graph = new Intent(BroadcastActivity.this, BroadcastActivity.class);
                    startActivity(graph);
                    break;
                case R.id.it_time_table:
                    Intent timeTable = new Intent(BroadcastActivity.this, TimeTableActivity.class);
                    startActivity(timeTable);
                    break;

                case R.id.it_log_out:
                    Intent logout = new Intent(BroadcastActivity.this, SigninRegActivity.class);
                    startActivity(logout);
                    break;
            }
            return true;
        });

//        IntentFilter filter = new IntentFilter("com.example.sampleproject.MY_BC");
//        registerReceiver(mBroadcast,filter);
        Intent intentService = new Intent(this, BackgroundService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intentService);
        }
        foregroundServiceRunning();
        dbGraphHelper = new DBGraphHelper(this);
        sqLiteDatabase = dbGraphHelper.getWritableDatabase();

        GraphView graph = (GraphView) findViewById(R.id.graph);
        GraphView graphTemp = (GraphView) findViewById(R.id.graph2);
        GraphView graphPress = (GraphView) findViewById(R.id.graph3);
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

        bargraphCustom(graphTemp, "Temperature", seriesTemp,0, 25, 0 );
        graphCustom(graph, "Humidity", series, 0, 100, 0);
        graphCustom(graphPress, "Wind speed", seriesPressure, 0,10,0);
    }

    private void bargraphCustom(GraphView graph, String name, BarGraphSeries<DataPoint> series1, int minY, int maxY, int minX) {
        series1.setTitle(name);
        series1.setColor(Color.GREEN);
        GridLabelRenderer gridLR = graph.getGridLabelRenderer();
        gridLR.setVerticalAxisTitleTextSize(70);
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.BLACK);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.BLACK);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().scrollToEnd();
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling

        // set manual X bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(minY);
        graph.getViewport().setMaxY(maxY);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(minX);

        // styling
        series1.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });
        series1.setSpacing(25);

        graph.getGridLabelRenderer().setGridColor(Color.BLACK);

    }

    private void graphCustom(GraphView graph, String name, LineGraphSeries<DataPoint> series1, int minY, int maxY, int minX) {
        series1.setTitle(name);
        series1.setColor(Color.rgb(53, 188, 255));
        GridLabelRenderer gridLR = graph.getGridLabelRenderer();
        String donvi;
        GridLabelRenderer gridLabelRenderer = graph.getGridLabelRenderer();
        gridLR.setVerticalAxisTitleTextSize(70);
        series1.setThickness(10);
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.BLACK);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.BLACK);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().scrollToEnd();
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling

        // set manual X bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(minY);
        graph.getViewport().setMaxY(maxY);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(minX);

        // styling series
        series.setColor(Color.BLACK);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);

// custom paint to make a dotted line
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
        series.setCustomPaint(paint);


        graph.getGridLabelRenderer().setGridColor(Color.BLACK);
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
//        Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
//        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();

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
    public boolean foregroundServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo serviceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (MyService.class.getName().equals(serviceInfo.service.getClassName())) {
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
            dp[i] = new DataPoint(i * 2, getdb.get(i));
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}