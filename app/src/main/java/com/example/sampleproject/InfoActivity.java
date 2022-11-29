package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sampleproject.Model.Info;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView version = (TextView)findViewById(R.id.txt_version);
        TextView url = (TextView)findViewById(R.id.txt_server_url);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Info> callInfo = apiInterface.getInfo();
        callInfo.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Info info = response.body();
                version.setText(info.getVersion());
                url.setText(info.getAuthServerURL());
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Log.d("API CALLa INFO", t.getMessage());
            }
        });


    }
}