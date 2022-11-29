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
import com.example.sampleproject.Adapter.RealmAccessAdapter;
import com.example.sampleproject.Model.RealmAccessible;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RealmAccessibleActivity extends AppCompatActivity {

    APIInterface apiInterface;
    private RecyclerView rcvRA;
    private List<RealmAccessible> listRA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_accessible);

        rcvRA = findViewById(R.id.recycler_view_realm_accessible);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvRA.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvRA.addItemDecoration(dividerItemDecoration);


        Button btnBack = (Button) findViewById(R.id.btn_back_from_realm_access);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(RealmAccessibleActivity.this, MainActivity.class);
                startActivity(back);
            }
        });

        apiInterface = APIClient.getClient().create(APIInterface.class);
        callApiGetRealmAccessible();
    }

    private void callApiGetRealmAccessible(){
        apiInterface.getRealmAccessible().enqueue(new Callback<List<RealmAccessible>>() {
            @Override
            public void onResponse(Call<List<RealmAccessible>> call, Response<List<RealmAccessible>> response) {
                 listRA = new ArrayList<>(response.body());
                RealmAccessAdapter ram = new RealmAccessAdapter(listRA);
                rcvRA.setAdapter(ram);
            }

            @Override
            public void onFailure(Call<List<RealmAccessible>> call, Throwable t) {
                Toast.makeText(RealmAccessibleActivity.this, "onFailure", Toast.LENGTH_LONG).show();
            }
        });
    }
}