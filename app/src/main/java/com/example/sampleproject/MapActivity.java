package com.example.sampleproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sampleproject.Model.Asset;
import com.example.sampleproject.Model.Attributes;
import com.example.sampleproject.Model.Default;
import com.example.sampleproject.Model.Location;
import com.example.sampleproject.Model.LocationValue;
import com.example.sampleproject.Model.Map;
import com.example.sampleproject.Model.Options;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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
    LinearLayout layoutBottomSheet;
    BottomSheetBehavior bottomSheetBehavior;
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
                Marker marker = new org.osmdroid.views.overlay.Marker(mapView);
                Drawable d = ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.marker_default, null);
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
        Call<Asset> assetloca1 = apiInterface.getAsset("6H4PeKLRMea1L0WsRXXWp9");
        assetloca1.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Gson gson = new Gson();
                String json = gson.toJson(asset.attributes);
                Attributes attr = gson.fromJson(json,Attributes.class);
                json = gson.toJson(attr.location);
                Location location = gson.fromJson(json, Location.class);
                json = gson.toJson(location.value);
                LocationValue lvalue = gson.fromJson(json,LocationValue.class);


                float lon =lvalue.coordinates[0];
                float lat =lvalue.coordinates[1];
                GeoPoint startPoint2 = new GeoPoint(lat, lon);
                mapView = findViewById(R.id.uitMap);
                mapController = mapView.getController();
                mapController.setZoom(20);
                mapController.setCenter(startPoint2);
                Marker marker = new org.osmdroid.views.overlay.Marker(mapView);
                Drawable d = ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.marker_default, null);
                Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
                Drawable dr = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (int) (13.0f * getResources().getDisplayMetrics().density), (int) (13.0f * getResources().getDisplayMetrics().density), true));
                marker.setIcon(dr);
                marker.setPosition(startPoint2);
                mapView.getOverlays().add(marker);
                marker.setTitle("WeatherAsset1");
                marker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

                  marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker, MapView mapView) {
                        clickOpenBottomSheetDialog();
//                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MapActivity.this, com.google.android.material.R.style.Base_Theme_Material3_Dark_BottomSheetDialog);
//                        layoutBottomSheet = (LinearLayout)findViewById(R.id.bottom_dialog);
//                        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
//                        if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
//                            bottomSheetBehavior.setState(bottomSheetBehavior.STATE_EXPANDED);
//                        }
//                        else{
//                            bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
//                        }
//                        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet, layoutBottomSheet);
//
//                        TextView id = (TextView) findViewById(R.id.asset_id);
//                        TextView version = (TextView) findViewById(R.id.asset_version);
//                        TextView createdOn = (TextView) findViewById(R.id.asset_created_on);
//                        id.setText(asset.id);
////                        version.setText(asset.version);
////                        createdOn.setText(asset.createdOn);

//                        bottomSheetDialog.setContentView(view);
//                        bottomSheetDialog.show();
                        return true;

                    }
                });

            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {

            }
        });
        Call<Asset> assetlocal2= apiInterface.getAsset("4cdWlxEvmDRBBDEc2HRsaF");
        assetlocal2.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Gson gson = new Gson();
                String json = gson.toJson(asset.attributes);
                Attributes attr = gson.fromJson(json,Attributes.class);
                json = gson.toJson(attr.location);
                Location location = gson.fromJson(json, Location.class);
                json = gson.toJson(location.value);
                LocationValue lvalue = gson.fromJson(json,LocationValue.class);


                float lon =lvalue.coordinates[0];
                float lat =lvalue.coordinates[1];
                GeoPoint startPoint2 = new GeoPoint(lat, lon);
                mapView = findViewById(R.id.uitMap);
                mapController = mapView.getController();
                mapController.setZoom(20);
                mapController.setCenter(startPoint2);
                Marker marker = new org.osmdroid.views.overlay.Marker(mapView);
                Drawable d = ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.marker_default, null);
                Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
                Drawable dr = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (int) (13.0f * getResources().getDisplayMetrics().density), (int) (13.0f * getResources().getDisplayMetrics().density), true));
                marker.setIcon(dr);
                marker.setPosition(startPoint2);
                mapView.getOverlays().add(marker);
                marker.setTitle("WeatherAsset1");
                marker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {

            }
        });
        Call<Asset> assetlocal3 = apiInterface.getAsset("2UZPM2Mvu11Xyq5jCWNMX1");
        assetlocal3.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Gson gson = new Gson();
                String json = gson.toJson(asset.attributes);
                Attributes attr = gson.fromJson(json,Attributes.class);
                json = gson.toJson(attr.location);
                Location location = gson.fromJson(json, Location.class);
                json = gson.toJson(location.value);
                LocationValue lvalue = gson.fromJson(json,LocationValue.class);


                float lon =lvalue.coordinates[0];
                float lat =lvalue.coordinates[1];
                GeoPoint startPoint2 = new GeoPoint(lat, lon);
                mapView = findViewById(R.id.uitMap);
                mapController = mapView.getController();
                mapController.setZoom(20);
                mapController.setCenter(startPoint2);
                Marker marker = new org.osmdroid.views.overlay.Marker(mapView);
                Drawable d = ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.marker_default, null);
                Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
                Drawable dr = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (int) (13.0f * getResources().getDisplayMetrics().density), (int) (13.0f * getResources().getDisplayMetrics().density), true));
                marker.setIcon(dr);
                marker.setPosition(startPoint2);
                mapView.getOverlays().add(marker);
                marker.setTitle("WeatherAsset1");
                marker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {

            }
        });
    }
    void clickOpenBottomSheetDialog(){
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

}