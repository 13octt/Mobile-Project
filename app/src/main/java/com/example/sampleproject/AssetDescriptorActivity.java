package com.example.sampleproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.Toast;

import com.example.sampleproject.Adapter.AssetDescriptorAdapter;
import com.example.sampleproject.Model.AssetDescriptor;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetDescriptorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private RecyclerView rcvAssetDescriptor;
    private List<AssetDescriptor> listAssetDescriptor;
    APIInterface apiInterface;
    DrawerLayout drawerLayout;
    ImageView imgMenu;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_descriptor);

        drawerLayout = findViewById(R.id.layout_ad);
        imgMenu = findViewById(R.id.ic_menu_ad);

        imgMenu.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
        NavigationView navigationView = findViewById(R.id.nav_view_ad);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.it_map:
                    Intent map = new Intent(AssetDescriptorActivity.this, MapActivity.class);
                    startActivity(map);
                    break;
                case R.id.it_weather:
                    Intent weather = new Intent(AssetDescriptorActivity.this, WeatherActivity.class);
                    startActivity(weather);
                    break;
                case R.id.it_user_role:
                    Intent userRoles = new Intent(AssetDescriptorActivity.this, UserRolesActivity.class);
                    startActivity(userRoles);
                    break;
                case R.id.it_asset_descriptor:
                    Intent des = new Intent(AssetDescriptorActivity.this, AssetDescriptorActivity.class);
                    startActivity(des);
                    break;
                case R.id.it_graph:
                    Intent graph = new Intent(AssetDescriptorActivity.this, BroadcastActivity.class);
                    startActivity(graph);
                    break;
                case R.id.it_time_table:
                    Intent timeTable = new Intent(AssetDescriptorActivity.this, TimeTableActivity.class);
                    startActivity(timeTable);
                    break;

                case R.id.it_log_out:
                    Intent logout = new Intent(AssetDescriptorActivity.this, SigninRegActivity.class);
                    startActivity(logout);
                    break;
            }
            return true;
        });

        rcvAssetDescriptor = findViewById(R.id.rcv_asset_descriptor);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvAssetDescriptor.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvAssetDescriptor.addItemDecoration(dividerItemDecoration);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        callApiGetAssetDescriptor();
    }

        private void callApiGetAssetDescriptor(){
            apiInterface.getAssetDescriptor().enqueue(new Callback<List<AssetDescriptor>>() {
                @Override
                public void onResponse(@NonNull Call<List<AssetDescriptor>> call, @NonNull Response<List<AssetDescriptor>> response) {
                    assert response.body() != null;
                    listAssetDescriptor = new ArrayList<>(response.body());
                    AssetDescriptorAdapter assetDescriptorAdapter = new AssetDescriptorAdapter(listAssetDescriptor);
                    rcvAssetDescriptor.setAdapter(assetDescriptorAdapter);
                }

                @Override
                public void onFailure(@NonNull Call<List<AssetDescriptor>> call, @NonNull Throwable t) {
                    Toast.makeText(AssetDescriptorActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                }
            });
        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}