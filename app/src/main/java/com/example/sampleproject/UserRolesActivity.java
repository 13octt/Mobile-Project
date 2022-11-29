package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.sampleproject.Adapter.UserRolesAdapter;
import com.example.sampleproject.Model.UserRoles;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRolesActivity extends AppCompatActivity {
    private RecyclerView rcvUserRoles;
    private List<UserRoles> listUR;
    APIInterface apiInterface;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_roles);
        

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
}