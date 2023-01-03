package com.example.sampleproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import com.example.sampleproject.helper.DBTimeTableHelper;
import com.google.android.material.navigation.NavigationView;

public class TimeTableActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DBTimeTableHelper dbTimeTableHelper ;
    DrawerLayout drawerLayout;
    ImageView imgMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        dbTimeTableHelper = new DBTimeTableHelper(this);

        drawerLayout = findViewById(R.id.layout_tt);
        imgMenu = findViewById(R.id.ic_menu_tt);

        imgMenu.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
        NavigationView navigationView = findViewById(R.id.nav_view_tt);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.it_map:
                    Intent map = new Intent(TimeTableActivity.this, MapActivity.class);
                    startActivity(map);
                    break;
                case R.id.it_weather:
                    Intent weather = new Intent(TimeTableActivity.this, WeatherActivity.class);
                    startActivity(weather);
                    break;
                case R.id.it_user_role:
                    Intent userRoles = new Intent(TimeTableActivity.this, UserRolesActivity.class);
                    startActivity(userRoles);
                    break;
//                case R.id.it_asset_descriptor:
//                    Intent des = new Intent(TimeTableActivity.this, AssetDescriptorActivity.class);
//                    startActivity(des);
//                    break;
                case R.id.it_time_table:
                    Intent timeTable = new Intent(TimeTableActivity.this, TimeTableActivity.class);
                    startActivity(timeTable);
                    break;
                case R.id.it_log_out:
                    Intent logout = new Intent(TimeTableActivity.this, SigninRegActivity.class);
                    startActivity(logout);
                    break;
            }
            return true;
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}