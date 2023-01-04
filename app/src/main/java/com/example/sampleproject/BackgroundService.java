package com.example.sampleproject;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.sampleproject.Model.Asset;
import com.example.sampleproject.Model.Attributes;
import com.example.sampleproject.Model.Main;
import com.example.sampleproject.Model.Sys;
import com.example.sampleproject.Model.Value;
import com.example.sampleproject.Model.Weather;
import com.example.sampleproject.Model.WeatherData;
import com.example.sampleproject.Model.Wind;
import com.example.sampleproject.helper.DBGraphHelper;

import org.osmdroid.views.overlay.LineDrawer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BackgroundService extends Service {
    private MyReceiver mBroadcast = new MyReceiver();
    String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
    Double humi, wind, temp;
    APIInterface apiInterface;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Service", "onCreate");
        IntentFilter filter = new IntentFilter("com.example.sampleproject.MY_BC");
        registerReceiver(mBroadcast, filter);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Intent intent_1 = new Intent("com.example.sampleproject.MY_BC");
//            sendBroadcast(intent_1);


        new Thread(
                new Runnable() {
                    @Override
                    public void run() {

                        while (true) {
                            getweather();
                            Intent intent1 = new Intent("com.example.sampleproject.MY_BC");
                            intent.putExtra("temp", temp);
                            intent.putExtra("humi", humi);
                            intent.putExtra("wind", wind);
                            if(currentTime=="03:10")
                                Log.e("time",wind.toString());


                            try {
                                sendBroadcast(intent1);
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }


                }
        ).start();
        final String CHANNEL_ID = "Channel Service";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            Notification.Builder notificate = new Notification.Builder(this, CHANNEL_ID);
            notificate.setContentText("App is running");
            notificate.setContentTitle("UIT get Asset");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            startForeground(1001, notificate.build());
        }
        return super.onStartCommand(intent, flags, startId);

    }

    public void getweather() {
        apiInterface = APIClient.getClient().create(APIInterface.class);


        Call<Asset> weather = apiInterface.getAsset("4cdWlxEvmDRBBDEc2HRsaF");
        weather.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Attributes attributes = asset.attributes;
                WeatherData weatherData = attributes.getWeatherData();
                Main main = weatherData.getValue().getMain();
                Value value = weatherData.getValue();
                Sys sys = value.getSys();
                temp = main.getTemp();
                Integer texmperature = (int) (temp - 0);
                Wind winde = value.getWind();
                wind = winde.getSpeed();
                humi = (double) main.getHumidity();
//                Weather weather1[] = value.getWeather().toArray(new Weather[0]);
//                String str = weather1[0].getDescription();
//                String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
//            txtTemp.setText(String.valueOf(temperature)+"°");
//            txtHumi.setText(String.valueOf(humidity)+"%");
//            txtSunrise.setText(String.valueOf(sunset));
//            txtSunset.setText(String.valueOf(sunrise));
//            txtWind.setText(String.valueOf(winspeed)+"km/h");
//            txtDes.setText(String.valueOf(cap));
//            txtMain.setText(String.valueOf(weather1[0].getMain()));


            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
                Log.d("API CALL ASSET", t.getMessage());
            }
        });

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}