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

import com.example.sampleproject.Model.Asset;
import com.example.sampleproject.Model.Attributes;
import com.example.sampleproject.Model.Coord;
import com.example.sampleproject.Model.Default;
import com.example.sampleproject.Model.Location;
import com.example.sampleproject.Model.LocationValue;
import com.example.sampleproject.Model.Main;
import com.example.sampleproject.Model.Map;
import com.example.sampleproject.Model.Options;
import com.example.sampleproject.Model.Value;
import com.example.sampleproject.Model.Value__1;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends AppCompatActivity{

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
                Options options = map.getoptions();
                Context ctx = getApplicationContext();
                Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
                Default defaultObj = options.get_default();
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
                Marker marker = new Marker(mapView);
                Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.map, null);
                Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
                Drawable dr = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (int) (13.0f * getResources().getDisplayMetrics().density), (int) (13.0f * getResources().getDisplayMetrics().density), true));
                marker.setIcon(dr);
                marker.setPosition(startPoint);
                mapView.getOverlays().add(marker);
                marker.setTitle("StartPoint");
                marker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                t.printStackTrace();
            }
        });

        addMarker("4cdWlxEvmDRBBDEc2HRsaF","1");
        addMarker("2UZPM2Mvu11Xyq5jCWNMX1","2");
        addMarker("6H4PeKLRMea1L0WsRXXWp9","3");
    }
    public  void  addMarker(String appid, String s){
        Call<Asset> assetloca1 = apiInterface.getAsset(appid);
        assetloca1.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Attributes attributes = asset.getAttributes();
                Location location = attributes.getLocation();
                Value__1 value = location.getValue();
                Double cord[] = value.getCoordinates().toArray(new Double[0]);


                Double lon =cord[0];
                Double lat =cord[1];
                GeoPoint startPoint2 = new GeoPoint(lat, lon);
                mapView = findViewById(R.id.uitMap);
                mapController = mapView.getController();
                mapController.setZoom(20);
                mapController.setCenter(startPoint2);
                Marker marker = new Marker(mapView);
                Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.map, null);
                Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
                Drawable dr = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (int) (13.0f * getResources().getDisplayMetrics().density), (int) (13.0f * getResources().getDisplayMetrics().density), true));
                marker.setIcon(dr);
                marker.setPosition(startPoint2);
                mapView.getOverlays().add(marker);
                marker.setTitle("WeatherAsset"+ s);
                marker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {

            }
        });
    }
}