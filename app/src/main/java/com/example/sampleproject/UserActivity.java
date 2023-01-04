package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sampleproject.Model.User;
import com.example.sampleproject.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserActivity extends AppCompatActivity {
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Button btnBack = (Button) findViewById(R.id.btn_back_from_user);

        TextView realm = (TextView) findViewById(R.id.tv_realm);
        TextView realmId = (TextView) findViewById(R.id.tv_readlmId);
        TextView id = (TextView) findViewById(R.id.tv_id);
        TextView enabled = (TextView) findViewById(R.id.tv_enabled);
        TextView createdOn = (TextView) findViewById(R.id.tv_created_on);
        TextView service = (TextView) findViewById(R.id.tv_service_account);
        TextView username = (TextView) findViewById(R.id.tv_username);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<User> callUser = apiInterface.getUser();
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("API CALL USER", response.body().toString());
                User user = response.body();
                realm.setText(user.getRealm());
                realmId.setText(user.getRealmId());
                id.setText(user.getId());
                enabled.setText(user.getEnabled());
                createdOn.setText(user.getCreatedOn());
                service.setText(user.getServiceAccount());
                username.setText(user.getUsername());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("CALL API USER", t.getMessage());
            }
        });

    }
}