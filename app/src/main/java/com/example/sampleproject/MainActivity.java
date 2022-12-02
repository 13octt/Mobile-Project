package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sampleproject.Model.Asset;
import com.example.sampleproject.Model.AssetDescriptor;
import com.example.sampleproject.Model.AssetUserCurrent;
import com.example.sampleproject.Model.Flow;
import com.example.sampleproject.Model.Info;
import com.example.sampleproject.Model.Map;
import com.example.sampleproject.Model.RealmAccessible;
import com.example.sampleproject.Model.User;
import com.example.sampleproject.Model.UserRoles;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = APIClient.getClient().create(APIInterface.class);

//        Call<Asset> call = apiInterface.getAsset("6H4PeKLRMea1L0WsRXXWp9");//, "Bearer "+ token);
//        call.enqueue(new Callback<Asset>() {
//            @Override
//            public void onResponse(Call<Asset> call, Response<Asset> response) {
//                Log.d ("APkkkI CsdasdALL ASSET", response.body()+ "");
//            }
//
//            @Override
//            public void onFailure(Call<Asset> call, Throwable t) {
//                Log.d("API CALL ASSET", t.getMessage().toString());
//                //t.printStackTrace();
//            }
//        });
//
//        Call<User> callUser = apiInterface.getUser();
//        callUser.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Log.d("API CALL USER", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.d("API CALL USER", t.getMessage().toString());
//            }
//        });
//
//        Call<List<Flow>> callFlow = apiInterface.getFlow();
//        callFlow.enqueue(new Callback<List<Flow>>() {
//            @Override
//            public void onResponse(Call<List<Flow>> call, Response<List<Flow>> response) {
//                Log.d("API CALL FLOW", response.body().toString());
//                //Flow flow = response.body();
//                //Log.d("API CALL FLOW", flow.getId() + "");
//            }
//
//            @Override
//            public void onFailure(Call<List<Flow>> call, Throwable t) {
//                Log.d("API CALL FLOW", t.getMessage());
//            }
//        });
//
//        Call<List<UserRoles>> calluserRoles = apiInterface.getUserRoles();
//        calluserRoles.enqueue(new Callback<List<UserRoles>>() {
//            @Override
//            public void onResponse(Call<List<UserRoles>> call, Response<List<UserRoles>> response) {
//                Log.d("API CALL USER ROLES", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<UserRoles>> call, Throwable t) {
//                Log.d("API CALL USER ROLES",t.getMessage());
//            }
//        });
//
//        Call<List<RealmAccessible>> realmAccessibleCall = apiInterface.getRealmAccessible();
//        realmAccessibleCall.enqueue(new Callback<List<RealmAccessible>>() {
//            @Override
//            public void onResponse(Call<List<RealmAccessible>> call, Response<List<RealmAccessible>> response) {
//                Log.d("API CALL REALM ACCESS", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<RealmAccessible>> call, Throwable t) {
//                Log.d("API CALL REALM ACCESS", t.getMessage());
//            }
//        });
//
//        Call<Info> callInfo = apiInterface.getInfo();
//        callInfo.enqueue(new Callback<Info>() {
//            @Override
//            public void onResponse(Call<Info> call, Response<Info> response) {
//                Log.d("API CALL INFO", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<Info> call, Throwable t) {
//                Log.d("API ALL INFO", t.getMessage());
//            }
//        });

        Call<Map> callMap = apiInterface.getMap();
        callMap.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                Log.d("API CALL MAP", response.body().toString());
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.d("API CALL MAP", t.getMessage());
            }
        });

//        Call<List<AssetUserCurrent>> callAssetUserCurrent = apiInterface.getAssetUserCurrent();
//        callAssetUserCurrent.enqueue(new Callback<List<AssetUserCurrent>>() {
//            @Override
//            public void onResponse(Call<List<AssetUserCurrent>> call, Response<List<AssetUserCurrent>> response) {
//                Log.d("API CALL A.USER CURRENT", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<AssetUserCurrent>> call, Throwable t) {
//                Log.d("API CALL A.USER CURRENT", t.getMessage().toString());
//            }
//        });
//
//        Call<List<AssetDescriptor>> callAssetDescriptor = apiInterface.getAssetDescriptor();
//        callAssetDescriptor.enqueue(new Callback<List<AssetDescriptor>>() {
//            @Override
//            public void onResponse(Call<List<AssetDescriptor>> call, Response<List<AssetDescriptor>> response) {
//                Log.d("API CALL A.DESCRIPTOR", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<AssetDescriptor>> call, Throwable t) {
//                Log.d("API CALL AL.DESCRIPTOR", t.getMessage().toString());
//            }
//        });

        Button btnGetAsset = (Button) findViewById(R.id.btn_get_asset);
        btnGetAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getAsset = new Intent(MainActivity.this, AssetActivity.class);
                startActivity(getAsset);
            }
        });

        Button btnGetInfo = (Button) findViewById(R.id.btn_get_info);
        btnGetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getInfo = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(getInfo);
            }
        });

        Button btnGetFlow = (Button) findViewById(R.id.btn_get_flow);
        btnGetFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getFlow = new Intent(MainActivity.this, FlowActivity.class);
                startActivity(getFlow);
            }
        });

        Button btnGetUser = (Button) findViewById(R.id.btn_get_user);
        btnGetUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getUser = new Intent(MainActivity.this, UserActivity.class);
                startActivity(getUser);
            }
        });

        Button btnGetAssetDescriptors = (Button) findViewById(R.id.btn_get_asset_descriptors);
        btnGetAssetDescriptors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getAssetDescriptors = new Intent(MainActivity.this, AssetDescriptorActivity.class);
                startActivity(getAssetDescriptors);
            }
        });

        Button btnGetRealmAccessible = (Button) findViewById(R.id.btn_get_realm_accessible);
        btnGetRealmAccessible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getRealmAccessible = new Intent(MainActivity.this, RealmAccessibleActivity.class);
                startActivity(getRealmAccessible);
            }
        });

        Button btnGetUserRoles  = (Button) findViewById(R.id.btn_get_user_roles);
        btnGetUserRoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getUserRoles = new Intent(MainActivity.this, UserRolesActivity.class);
                startActivity(getUserRoles);
            }
        });

        Button btnGetMap = (Button) findViewById(R.id.btn_get_map);
        btnGetMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getMap = new Intent(MainActivity.this, MapActivity.class);
                startActivity(getMap);
            }
        });

        Button btnGetAssetUserCurrent = (Button) findViewById(R.id.btn_get_asset_user_current);
        btnGetAssetUserCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getAssetUserCurrent = new Intent(MainActivity.this, AssetUserCurrentActivity.class);
                startActivity(getAssetUserCurrent);
            }
        });

        Button btnGetValueDescriptor = (Button) findViewById(R.id.btn_get_value_descriptor);
        btnGetValueDescriptor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getValueDescriptor = new Intent(MainActivity.this, ValueDescriptorActivity.class);
                startActivity(getValueDescriptor);
            }
        });

        Button btnGetMetaItemDescriptorActivity = (Button)findViewById(R.id.btn_get_meta_item_descriptor);
        btnGetMetaItemDescriptorActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent getMetaItemDescriptor = new Intent(MainActivity.this, MetaItemDescriptorActivity.class);
//                startActivity(getMetaItemDescriptor);
            }
        });

        Button btnHomeScreen = (Button) findViewById(R.id.layout_home_screen);
        btnHomeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeScreen = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeScreen);
            }
        });
    }
}