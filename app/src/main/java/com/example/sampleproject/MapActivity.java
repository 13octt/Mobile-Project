package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sampleproject.Model.Map;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;

import okio.Options;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapActivity extends AppCompatActivity {

    APIInterface apiInterface;
    private MapView mapView;
    private IMapController mapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Call<Map> callMap = apiInterface.getMap();
        callMap.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                Map map = response.body();
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.d("API CALL MAP", t.getMessage().toString());
            }
        });


    }
}