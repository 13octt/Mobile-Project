package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sampleproject.Model.Asset;


import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetActivity extends AppCompatActivity {
    APIInterface apiInterface;
    DateTimeFormatter dateTimeFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);

        TextView txtId = (TextView) findViewById(R.id.asset_id);
        TextView txtVersion = (TextView)findViewById(R.id.asset_version);
        TextView txtCreatedOn = (TextView)findViewById(R.id.asset_created_on);
        TextView txtName = (TextView) findViewById(R.id.asset_name);
        TextView txtIsPublic = (TextView)findViewById(R.id.asset_access_public_read);
        TextView txtParentId = (TextView)findViewById(R.id.asset_parent_id);
        TextView txtRealm = (TextView)findViewById(R.id.asset_realm);
        TextView txtType = (TextView)findViewById(R.id.asset_type);

        Button btnBack = (Button) findViewById(R.id.btn_back_from_asset);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(AssetActivity.this, MainActivity.class);
                startActivity(back);
            }
        });


        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Asset> callAsset = apiInterface.getAsset("6H4PeKLRMea1L0WsRXXWp9");
        callAsset.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                txtId.setText(asset.id);
                txtVersion.setText(asset.version);
                txtCreatedOn.setText(asset.createdOn);
                txtName.setText(asset.name);
                txtIsPublic.setText(asset.accessPublicRead);
                txtParentId.setText(asset.parentID);
                txtRealm.setText(asset.realm);
                txtType.setText(asset.type);
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
                Log.d("API CALddsfL ASSET", t.getMessage());
            }
        });


    }
}