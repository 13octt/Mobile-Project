package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject.Model.Asset;
import com.example.sampleproject.Model.Attributes;
import com.example.sampleproject.Model.Main;
import com.example.sampleproject.Model.Sys;
import com.example.sampleproject.Model.Value;
import com.example.sampleproject.Model.Weather;
import com.example.sampleproject.Model.WeatherData;
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
String apikey="2UZPM2Mvu11Xyq5jCWNMX1";

APIInterface apiInterface;

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
        getweather(apikey);

    }
    public void getweather(String appid){
        apiInterface = APIClient.getClient().create(APIInterface.class);


        Call<Asset> weather = apiInterface.getAsset(appid);
        weather.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Attributes attributes = asset.attributes;
                WeatherData weatherData = attributes.getWeatherData();
                Main main = weatherData.getValue().getMain();
                Value value = weatherData.getValue();
                Sys sys = value.getSys();
                Double temp = main.getTemp();
                Integer temperature = (int)(temp-0);
                Wind wind = value.getWind();
                Double winspeed = wind.getSpeed();
                Integer humidity = main.getHumidity();
                String sunrise =covertUnixToHour(sys.getSunrise()) ;
                String sunset = covertUnixToHour(sys.getSunset());

                txtTemp.setText(String.valueOf(temperature)+"°");
                txtHumi.setText(String.valueOf(humidity)+"%");
                txtSunrise.setText(String.valueOf(sunset));
                txtSunset.setText(String.valueOf(sunrise));
                txtWind.setText(String.valueOf(winspeed)+"km/h");




            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
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