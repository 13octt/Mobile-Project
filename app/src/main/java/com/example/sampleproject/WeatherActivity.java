package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject.Model.Main;
import com.example.sampleproject.Model.Sys;
import com.example.sampleproject.Model.Weather;
import com.example.sampleproject.Model.Wind;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {
String url = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}";
String apikey="c3ecbf09be5267cd280676a01acd3360";
String lon="105.8544441";
String lat="21.0294498";
TextView txtTemp,txtWind, txtHumi, txtSunrise,txtSunset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        txtTemp = (TextView) findViewById(R.id.tvTemp);
        txtWind = (TextView) findViewById(R.id.tvWind);
        txtHumi = (TextView) findViewById(R.id.tvHumi);
        txtSunrise = (TextView) findViewById(R.id.tvSR);
        txtSunset = (TextView) findViewById(R.id.tvSS);


        getweather();
    }
    public void getweather(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIInterface myapi = retrofit.create(APIInterface.class);
        Call<Weather> weather = myapi.getweather(lat,lon,apikey);
        weather.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather mydata = response.body();
                Main main = mydata.getMain();
                Sys sys = mydata.getSys();
                Double temp = main.getTemp();
                Integer temperature = (int)(temp-273.15);
                Wind wind = mydata.getWind();
                Double winspeed = wind.getSpeed();
                Integer humidity = main.getHumidity();
                String sunrise =covertUnixToHour(sys.getSunrise()) ;
                String sunset = covertUnixToHour(sys.getSunset());


                txtTemp.setText(String.valueOf(temperature)+"°C");
                txtHumi.setText(String.valueOf(humidity)+"%");
                txtSunrise.setText(String.valueOf(sunset));
                txtSunset.setText(String.valueOf(sunrise));
                txtWind.setText(String.valueOf(winspeed)+"km/h");




            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d("API CALL ASSET", t.getMessage());

            }
        });

    }
    public static String covertUnixToHour(int sunrise){
        Date date = new Date(sunrise* 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formatted = sdf.format(date);
        return formatted;
    }
}