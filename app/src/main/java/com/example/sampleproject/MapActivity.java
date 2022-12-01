package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.sampleproject.Model.Map;
import com.example.sampleproject.Model.Options;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;


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

        Context context = getApplicationContext();
//        Configuration.getInstance().load

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Map> callMap = apiInterface.getMap();
        callMap.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
//                Map map = response.body();
//                Gson gson = new Gson();
//                String json = gson.toJson(map.options);
//                Options optionsObj = gson.fromJson(json, Options.class);
//                json = gson.toJson(optionsObj.defaultOption);
//                Default defaultObj = gson.fromJson(json, Default.class);
//                Double zoom = defaultObj.zoom;
//                Float longitude = defaultObj.center[0];
//                Float latitude = defaultObj.center[1];
//                mapView = findViewById(R.id.uitMap);
//                mapView.setTileSource(TileSourceFactory.MAPNIK);
//                mapView.setBuiltInZoomControls(true);
//                mapView.setMultiTouchControls(true);
//                mapController = mapView.getController();
//                mapController.setZoom(zoom);
//                GeoPoint startPoint = new GeoPoint(latitude, longitude);
//                mapController.setCenter(startPoint);
//                Marker marker = new Marker(mapView);
//                marker.setPosition(startPoint);
//                mapView.getOverlays().add(marker);
//                marker.setTitle("Nhà xe Nhân văn :V");
//                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {

            }
        });
    }
}