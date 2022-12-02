package com.example.sampleproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.example.sampleproject.Model.Default;
import com.example.sampleproject.Model.Map;
import com.example.sampleproject.Model.Options;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    APIInterface apiInterface;
    private MapView mapView;
    private IMapController mapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);



        Context ctx = getApplicationContext();
        org.osmdroid.config.Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        apiInterface = APIClient.getClient().create(APIInterface.class);
//        NavigationView nv = findViewById(R.id.navi);
//        nv.setNavigationItemSelectedListener(this);
        Call<Map> mapCall = apiInterface.getMap();
        mapCall.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                Map map = response.body();
                Gson gson = new Gson();
                String json = gson.toJson(map.options);
                Context ctx = getApplicationContext();
                Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
                Options optionsObj = gson.fromJson(json, Options.class);
                json = gson.toJson(optionsObj._default);
                Default defaultObj = gson.fromJson(json, Default.class);
                Double zoom = defaultObj.zoom;
                Float longitude = defaultObj.center[0];
                Float latitude = defaultObj.center[1];
                mapView = findViewById(R.id.uitMap);
                mapView.setTileSource(TileSourceFactory.MAPNIK);
                mapView.setMultiTouchControls(true);
                mapController = mapView.getController();
                mapController.setZoom(20);
                GeoPoint startPoint = new GeoPoint(latitude, longitude);
                mapController.setCenter(startPoint);
                org.osmdroid.views.overlay.Marker marker = new org.osmdroid.views.overlay.Marker(mapView);
                Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.cloud, null);
                Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
                Drawable dr = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (int) (13.0f * getResources().getDisplayMetrics().density), (int) (13.0f * getResources().getDisplayMetrics().density), true));
                marker.setIcon(dr);
                marker.setPosition(startPoint);
                mapView.getOverlays().add(marker);
                marker.setTitle("Nhà xe Nhân văn :V");
                marker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_BOTTOM);
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
    public Marker addMarker (GeoPoint p, MapView osm){
        Marker marker = new Marker(osm);
        marker.setPosition(p);
        osm.getOverlays().add(marker);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        return marker;
    }
    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}