package com.example.sampleproject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.sampleproject.Model.Asset;
import com.example.sampleproject.Model.Attributes;
import com.example.sampleproject.Model.Default;
import com.example.sampleproject.Model.Location1;
import com.example.sampleproject.Model.Map;
import com.example.sampleproject.Model.Options;
import com.example.sampleproject.Model.Value__1;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
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
    ImageButton getmylocation;
    private  final  static int REQUEST_CODE=100;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        drawerLayout = findViewById(R.id.layout_dialog);
        imgMenu = findViewById(R.id.ic_menu);
        getmylocation = (ImageButton) findViewById(R.id.btn_getlocation) ;
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
                    case R.id.it_graph:
                        Intent graph = new Intent(MapActivity.this, BroadcastActivity.class);
                        startActivity(graph);
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
                mapView.setMultiTouchControls(true);
                mapController = mapView.getController();
                mapController.setZoom(20);
                mapView.setTileSource(TileSourceFactory.MAPNIK);

                GeoPoint startPoint = new GeoPoint(latitude, longitude);
                mapController.setCenter(startPoint);
                Marker marker = new Marker(mapView);
                Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.location_pin, null);
                Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
                Drawable dr = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (int) (13.0f * getResources().getDisplayMetrics().density), (int) (13.0f * getResources().getDisplayMetrics().density), true));
                marker.setIcon(dr);
                marker.setPosition(startPoint);
                mapView.getOverlays().add(marker);
                marker.setTitle("Nhân Văn nè");
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
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);

        getmylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
            }
        });
    }

    public void addMarker(String appid, String s) {

        Call<Asset> assetloca1 = apiInterface.getAsset(appid);
        assetloca1.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Attributes attributes = asset.getAttributes();
                Location1 location = attributes.getLocation();
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
                Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.location_pin, null);
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
//                        bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.RED));
                        TextView id = (TextView) view.findViewById(R.id.asset_id);
                        TextView version = (TextView) view.findViewById(R.id.asset_version);
                        TextView createdOn = (TextView) view.findViewById(R.id.asset_created_on);
                        TextView parent_id = (TextView) view.findViewById(R.id.asset_parent_id);
                        TextView name = (TextView) view.findViewById(R.id.asset_name);
                        TextView public_read = (TextView) view.findViewById(R.id.asset_access_public_read);
                        TextView realm = (TextView) view.findViewById(R.id.asset_realm);
                        TextView type = (TextView) view.findViewById(R.id.asset_type);
//                        TextView path = (TextView) view.findViewById(R.id.asset_path);
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
    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location !=null){
                                Geocoder geocoder=new Geocoder(MapActivity.this, Locale.getDefault());
                                List<Address> addresses= null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                    Double latitude = addresses.get(0).getLatitude();
                                    Double longitude = addresses.get(0).getLongitude();

                                    GeoPoint startPoint2 = new GeoPoint(latitude, longitude);
                                    mapView = findViewById(R.id.uitMap);
                                    mapController = mapView.getController();
                                    mapController.setZoom(20);
                                    mapController.setCenter(startPoint2);
//                                    return addr;

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }



                            }

                        }
                    });


        }else
        {

            askPermission();

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode==REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }
            else {
                Toast.makeText(this, "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }






        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(MapActivity.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}