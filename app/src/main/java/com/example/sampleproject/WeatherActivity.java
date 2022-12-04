package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.sampleproject.Model.Main;
import com.example.sampleproject.Model.Weather;

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
TextView txtTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        txtTemp = (TextView) findViewById(R.id.TVTemperature);
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
                Double temp = main.getTemp();
                Integer temperature = (int)(temp-273.15);
                txtTemp.setText(String.valueOf(temperature)+"C");
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d("API CALL ASSET", t.getMessage());

            }
        });
    }
}