package com.example.sampleproject;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject.helper.DBTimeTableHelper;
import com.google.android.material.navigation.NavigationView;

public class TimeTableActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DBTimeTableHelper dbTimeTableHelper ;
    Cursor cursor;

    SQLiteDatabase sqLiteDatabase;
    Button addSubject, deleteSubject;
    TableLayout tableLayout;
    public String edit;
    public TextView etMon1;
//    EditText usrname, day , period_begin,period_end,subject,password, repassword;
    Button insertTable;
    DrawerLayout drawerLayout;
    ImageView imgMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        dbTimeTableHelper = new DBTimeTableHelper(this);


        drawerLayout = findViewById(R.id.layout_tt);
        imgMenu = findViewById(R.id.ic_menu_tt);

        imgMenu.setOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
        NavigationView navigationView = findViewById(R.id.nav_view_tt);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                    case R.id.it_asset_descriptor:
                        Intent des = new Intent(TimeTableActivity.this, AssetDescriptorActivity.class);
                        startActivity(des);
                        break;
                    case R.id.it_graph:
                        Intent graph = new Intent(TimeTableActivity.this, BroadcastActivity.class);
                        startActivity(graph);
                        break;
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
            }

        });


        insertTable = findViewById(R.id.btn_add_class);
        insertTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent custom = new Intent(TimeTableActivity.this, custom.class);
                startActivity(custom);
            }
        });
//        TextView tt = (TextView) findViewById(R.id.txt_time_table);
//        //button add
//        addSubject = (Button) findViewById(R.id.button_add) ;
    Button btn_mon = (Button) findViewById(R.id.mondaybtn);
    Button btn_tue = (Button) findViewById(R.id.tuesdaybtn);
    Button btn_wed = (Button) findViewById(R.id.wednesdaybtn);
    Button btn_thur = (Button) findViewById(R.id.thursday);
    Button btn_fri = (Button) findViewById(R.id.fridaybtn);
    Button btn_sat = (Button) findViewById(R.id.saturday);
    click_listener(btn_mon,"Monday");
    click_listener(btn_tue,"Tuesday");
    click_listener(btn_wed,"Wednesday");
    click_listener(btn_thur,"Thursday");
    click_listener(btn_fri,"Friday");
    click_listener(btn_sat,"Saturday");



    }
    public void click_listener(Button button, String string){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView name = (TextView) findViewById(R.id.usroles_name);
                TextView name1 = (TextView) findViewById(R.id.usroles_name2);
                TextView sub = (TextView) findViewById(R.id.usroles_id);
                TextView sub1 = (TextView) findViewById(R.id.usroles_id2);
                TextView begin = (TextView) findViewById(R.id.usroles_description);
                TextView begin1 = (TextView) findViewById(R.id.usroles_description2);
                TextView end = (TextView) findViewById(R.id.usroles_composite);
                TextView end1 = (TextView) findViewById(R.id.usroles_composite2);

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.class1);
                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.class2);
                int i = 0;
                cursor = dbTimeTableHelper.getData("SELECT * FROM time_table WHERE day =" + "'" + string + "'");
                while (cursor.moveToNext()) {
                    String str = cursor.getString(0);
                    String str1 = cursor.getString(1);
                    String str2 = cursor.getString(2);
                    String str3 = cursor.getString(3);
                    if (i == 0) {
                        sub.setText(str);
                        name.setText(str1);
                        begin.setText(str2);
                        end.setText(str3);
                        i++;
                        Toast.makeText(TimeTableActivity.this, "done", Toast.LENGTH_SHORT).show();
                        relativeLayout.setBackgroundColor(Color.rgb(30,42,65));
                    }
                    if (i == 1) {
                        sub1.setText(str);
                        name1.setText(str1);
                        begin1.setText(str2);
                        end1.setText(str3);
                        relativeLayout.setBackgroundColor(Color.rgb(30,42,65));

                        i++;

                    } else {
                        i = 0;
                    }

                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}