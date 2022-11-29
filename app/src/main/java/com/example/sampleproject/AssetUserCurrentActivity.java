package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject.Adapter.AssetUserCurrentAdapter;
import com.example.sampleproject.Model.AssetUserCurrent;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetUserCurrentActivity extends AppCompatActivity {

    private RecyclerView rcvAssetUserCurrent;
    private List<AssetUserCurrent> listAssetUserCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_user_current);

        Button btnBack = (Button)findViewById(R.id.btn_back_from_user_current);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(AssetUserCurrentActivity.this, MainActivity.class);
                startActivity(back);
            }
        });

        rcvAssetUserCurrent = findViewById(R.id.rcv_asset_user_current);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvAssetUserCurrent.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvAssetUserCurrent.addItemDecoration(dividerItemDecoration);

        callApiAssetUserCurrent();

    }

    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    private void callApiAssetUserCurrent(){
        apiInterface.getAssetUserCurrent().enqueue(new Callback<List<AssetUserCurrent>>() {
            @Override
            public void onResponse(Call<List<AssetUserCurrent>> call, Response<List<AssetUserCurrent>> response) {
                listAssetUserCurrent = new ArrayList<>(response.body());
                AssetUserCurrentAdapter assetUserCurrentAdapter = new AssetUserCurrentAdapter(listAssetUserCurrent);
                rcvAssetUserCurrent.setAdapter(assetUserCurrentAdapter);
            }

            @Override
            public void onFailure(Call<List<AssetUserCurrent>> call, Throwable t) {
                Toast.makeText(AssetUserCurrentActivity.this, "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }

}