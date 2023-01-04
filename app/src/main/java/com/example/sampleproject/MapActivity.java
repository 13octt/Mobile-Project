package com.example.sampleproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    APIInterface apiInterface;
    private MapView mapView;
    private IMapController mapController;
    private Broadcast mBroadcast;
    DrawerLayout drawerLayout;
    ImageView imgMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        drawerLayout = findViewById(R.id.layout_dialog);
        imgMenu = findViewById(R.id.ic_menu);

        imgMenu.setOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.it_map:
                        Intent map = new Intent(MapActivity.this, MapActivity.class);
                        startActivity(map);
                        break;
                    case R.id.it_weather:
                        Intent weather = new Intent(MapActivity.this, WeatherActivity.class);
                        startActivity(weather);
                        break;
                    case R.id.it_user_role:
                        Intent userRoles = new Intent(MapActivity.this, UserRolesActivity.class);
                        startActivity(userRoles);
                        break;
                    case R.id.it_asset_descriptor:
                        Intent des = new Intent(MapActivity.this, AssetDescriptorActivity.class);
                        startActivity(des);
                        break;
                    case R.id.it_time_table:
                        Intent timeTable = new Intent(MapActivity.this, TimeTableActivity.class);
                        startActivity(timeTable);
                        break;

                    case R.id.it_log_out:
                        Intent logout = new Intent(MapActivity.this, SigninRegActivity.class);
                        startActivity(logout);
                        break;
                }
                return true;
            }

        });

        mBroadcast = new Broadcast();
        IntentFilter intent = new IntentFilter(".Insight");
        registerReceiver(mBroadcast, intent);

        Context ctx = getApplicationContext();
        org.osmdroid.config.Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        apiInterface = APIClient.getClient().create(APIInterface.class);

//        NavigationView nv = findViewById(R.id.bottom_nav_bar);
//        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                return true;
//            }
//        });

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

        addMarker("4cdWlxEvmDRBBDEc2HRsaF", "1");
        addMarker("2UZPM2Mvu11Xyq5jCWNMX1", "2");
        addMarker("6H4PeKLRMea1L0WsRXXWp9", "3");
    }

    public void addMarker(String appid, String s) {

        Call<Asset> assetloca1 = apiInterface.getAsset(appid);
        assetloca1.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Attributes attributes = asset.getAttributes();
                Location location = attributes.getLocation();
                Value__1 value = location.getValue();
                Double cord[] = value.getCoordinates().toArray(new Double[0]);

                Double lon = cord[0];
                Double lat = cord[1];
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
                marker.setTitle(asset.getId());
                marker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker, MapView mapView) {
                        View view = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MapActivity.this);

                        TextView id = (TextView) view.findViewById(R.id.asset_id);
                        TextView version = (TextView) view.findViewById(R.id.asset_version);
                        TextView createdOn = (TextView) view.findViewById(R.id.asset_created_on);
                        TextView parent_id = (TextView) view.findViewById(R.id.asset_parent_id);
                        TextView name = (TextView) view.findViewById(R.id.asset_name);
                        TextView public_read = (TextView) view.findViewById(R.id.asset_access_public_read);
                        TextView realm = (TextView) view.findViewById(R.id.asset_realm);
                        TextView type = (TextView) view.findViewById(R.id.asset_type);
                        TextView path = (TextView) view.findViewById(R.id.asset_path);
                        String crea = formatDate(Long.valueOf(asset.createdOn));
                        id.setText(asset.getId());
                        version.setText(String.valueOf(asset.getVersion()));
                        createdOn.setText(String.valueOf(crea));
                        parent_id.setText(String.valueOf(asset.parentID));
                        name.setText(String.valueOf(asset.name));
                        public_read.setText(String.valueOf(asset.accessPublicRead));
                        realm.setText(String.valueOf(asset.realm));
                        type.setText(String.valueOf(asset.type));
//                        path.setText(String.valueOf(asset.getParentID()));

                        bottomSheetDialog.setContentView(view);
                        bottomSheetDialog.show();
                        Button btn_more = (Button) view.findViewById(R.id.btn_more);
                        btn_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MapActivity.this, WeatherActivity.class);
                                intent.putExtra("Key", appid);
                                startActivity(intent);
                            }
                        });

                        return true;
                    }
                });
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {

            }
        });
    }

    private String formatDate(long milliseconds) /* This is your topStory.getTime()*1000 */ {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        TimeZone tz = TimeZone.getDefault();
        sdf.setTimeZone(tz);
        return sdf.format(calendar.getTime());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}