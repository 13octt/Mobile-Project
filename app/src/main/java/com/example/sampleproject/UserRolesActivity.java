package com.example.sampleproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject.Adapter.UserRolesAdapter;
import com.example.sampleproject.Model.UserRoles;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRolesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView rcvUserRoles;
    private List<UserRoles> listUR;
    APIInterface apiInterface;

    DrawerLayout drawerLayout;
    ImageView imgMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_roles);

        drawerLayout = findViewById(R.id.layout_user_roles);
        imgMenu = findViewById(R.id.ic_menu);

        imgMenu.setOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
        NavigationView navigationView = findViewById(R.id.nav_view_ur);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.it_map:
                        Intent map = new Intent(UserRolesActivity.this, MapActivity.class);
                        startActivity(map);
                        break;
                    case R.id.it_weather:
                        Intent weather = new Intent(UserRolesActivity.this, WeatherActivity.class);
                        startActivity(weather);
                        break;
                    case R.id.it_user_role:
                        Intent userRoles = new Intent(UserRolesActivity.this, UserRolesActivity.class);
                        startActivity(userRoles);
                        break;
                    case R.id.it_asset_descriptor:
                        Intent des = new Intent(UserRolesActivity.this, AssetDescriptorActivity.class);
                        startActivity(des);
                        break;
                    case R.id.it_graph:
                        Intent graph = new Intent(UserRolesActivity.this, BroadcastActivity.class);
                        startActivity(graph);
                        break;
                    case R.id.it_time_table:
                        Intent timeTable = new Intent(UserRolesActivity.this, TimeTableActivity.class);
                        startActivity(timeTable);
                        break;

                    case R.id.it_log_out:
                        Intent logout = new Intent(UserRolesActivity.this, SigninRegActivity.class);
                        startActivity(logout);
                        break;
                }
                return true;
            }

        });

        rcvUserRoles = findViewById(R.id.rcv_user_roles);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUserRoles.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvUserRoles.addItemDecoration(dividerItemDecoration);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        callApiGetUserRoles();
        
    }
     private void callApiGetUserRoles() {
        apiInterface.getUserRoles().enqueue(new Callback<List<UserRoles>>() {
            @Override
            public void onResponse(Call<List<UserRoles>> call, Response<List<UserRoles>> response) {
                listUR = new ArrayList<>(response.body());
                UserRolesAdapter userRoles = new UserRolesAdapter(listUR);
                rcvUserRoles.setAdapter(userRoles);
            }

            @Override
            public void onFailure(Call<List<UserRoles>> call, Throwable t) {
                Toast.makeText(UserRolesActivity.this, "onFailure", Toast.LENGTH_LONG).show();
            }
        });
     }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}