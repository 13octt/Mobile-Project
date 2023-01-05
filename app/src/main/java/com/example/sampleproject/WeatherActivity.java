package com.example.sampleproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sampleproject.Model.Asset;
import com.example.sampleproject.Model.Attributes;
import com.example.sampleproject.Model.Main;
import com.example.sampleproject.Model.Sys;
import com.example.sampleproject.Model.Value;
import com.example.sampleproject.Model.Weather;
import com.example.sampleproject.Model.WeatherData;
import com.example.sampleproject.Model.Wind;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String apikey = "4cdWlxEvmDRBBDEc2HRsaF";
    DrawerLayout drawerLayout;
    ImageView imgMenu;

    APIInterface apiInterface;

    TextView txtTemp, txtWind, txtHumi, txtSunrise, txtSunset, txtMain, txtDes;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        drawerLayout = findViewById(R.id.layout_weather);
        imgMenu = findViewById(R.id.ic_menu);

        imgMenu.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
        NavigationView navigationView = findViewById(R.id.nav_view_weather);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.it_map:
                    Intent map = new Intent(WeatherActivity.this, MapActivity.class);
                    startActivity(map);
                    break;
                case R.id.it_weather:
                    Intent weather = new Intent(WeatherActivity.this, WeatherActivity.class);
                    startActivity(weather);
                    break;
                case R.id.it_user_role:
                    Intent userRoles = new Intent(WeatherActivity.this, UserRolesActivity.class);
                    startActivity(userRoles);
                    break;
                case R.id.it_asset_descriptor:
                    Intent des = new Intent(WeatherActivity.this, AssetDescriptorActivity.class);
                    startActivity(des);
                    break;
                case R.id.it_graph:
                    Intent graph = new Intent(WeatherActivity.this, BroadcastActivity.class);
                    startActivity(graph);
                    break;
                case R.id.it_time_table:
                    Intent timeTable = new Intent(WeatherActivity.this, TimeTableActivity.class);
                    startActivity(timeTable);
                    break;

                case R.id.it_log_out:
                    Intent logout = new Intent(WeatherActivity.this, SigninRegActivity.class);
                    startActivity(logout);
                    break;
            }
            return true;
        });

        txtTemp = findViewById(R.id.tvTemp);
        txtWind = findViewById(R.id.tvWind);
        txtHumi = findViewById(R.id.tvHumi);
        txtSunrise = findViewById(R.id.tvSR);
        txtSunset = findViewById(R.id.tvSS);
        txtMain = findViewById(R.id.tvMain);
        txtDes = findViewById(R.id.tvDes);

        Intent intent = getIntent();
        apikey = intent.getStringExtra("Key");
        if(apikey==null)
            apikey = "4cdWlxEvmDRBBDEc2HRsaF";
        getweather(apikey);

    }

    public void getweather(String appid) {
        apiInterface = APIClient.getClient().create(APIInterface.class);


        Call<Asset> weather = apiInterface.getAsset(appid);
        weather.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(@NonNull Call<Asset> call, @NonNull Response<Asset> response) {
                Asset asset = response.body();
                Attributes attributes = asset.attributes;
                WeatherData weatherData = attributes.getWeatherData();
                Main main = weatherData.getValue().getMain();
                Value value = weatherData.getValue();
                Sys sys = value.getSys();
                Double temp = main.getTemp();
                Integer temperature = (int) (temp - 0);
                Wind wind = value.getWind();
                Double winspeed = wind.getSpeed();
                Integer humidity = main.getHumidity();
                String sunrise = covertUnixToHour(sys.getSunrise());
                String sunset = covertUnixToHour(sys.getSunset());
                Weather weather1[] = value.getWeather().toArray(new Weather[0]);
                String str = weather1[0].getDescription();
                String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
                txtTemp.setText(temperature + "°");
                txtHumi.setText(humidity + "%");
                txtSunrise.setText(sunset);
                txtSunset.setText(sunrise);
                txtWind.setText(winspeed + "km/h");
                txtDes.setText(cap);
                txtMain.setText(String.valueOf(weather1[0].getMain()));


            }

            @Override
            public void onFailure(@NonNull Call<Asset> call, @NonNull Throwable t) {
                Log.d("API CALL ASSET", t.getMessage());

            }
        });

    }

    public static String covertUnixToHour(int sunrise) {
        Date date = new Date(sunrise * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formatted = sdf.format(date);
        return formatted;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}