package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sampleproject.Adapter.AssetDescriptorAdapter;
import com.example.sampleproject.Model.AssetDescriptor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetDescriptorActivity extends AppCompatActivity {
    private RecyclerView rcvAssetDescriptor;
    private List<AssetDescriptor> listAssetDescriptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_descriptor);

        Button btnBack = (Button)findViewById(R.id.btn_back_from_asset_descriptor);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(AssetDescriptorActivity.this, MainActivity.class);
                startActivity(back);
            }
        });

        rcvAssetDescriptor = findViewById(R.id.rcv_asset_descriptor);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvAssetDescriptor.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvAssetDescriptor.addItemDecoration(dividerItemDecoration);
        callApiGetAssetDescriptor();
    }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        private void callApiGetAssetDescriptor(){
            apiInterface.getAssetDescriptor().enqueue(new Callback<List<AssetDescriptor>>() {
                @Override
                public void onResponse(Call<List<AssetDescriptor>> call, Response<List<AssetDescriptor>> response) {
                    listAssetDescriptor = new ArrayList<>(response.body());
                    AssetDescriptorAdapter assetDescriptorAdapter = new AssetDescriptorAdapter(listAssetDescriptor);
                    rcvAssetDescriptor.setAdapter(assetDescriptorAdapter);
                }

                @Override
                public void onFailure(Call<List<AssetDescriptor>> call, Throwable t) {
                    Toast.makeText(AssetDescriptorActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                }
            });
        }
}