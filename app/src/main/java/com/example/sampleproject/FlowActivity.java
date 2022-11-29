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

import com.example.sampleproject.Adapter.FlowAdapter;
import com.example.sampleproject.Model.Flow;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlowActivity extends AppCompatActivity {

    private RecyclerView rcvFlow;
    private List<Flow> listFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);

        Button btnBack = (Button) findViewById(R.id.btn_back_from_flow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(FlowActivity.this, MainActivity.class);
                startActivity(back);
            }
        });

        rcvFlow = findViewById(R.id.rcv_flow);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvFlow.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvFlow.addItemDecoration(dividerItemDecoration);
        callApiGetFlow();
    }
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    private void callApiGetFlow(){
        apiInterface.getFlow().enqueue(new Callback<List<Flow>>() {
            @Override
            public void onResponse(Call<List<Flow>> call, Response<List<Flow>> response) {
                listFlow = new ArrayList<>(response.body());
                FlowAdapter flowAdapter = new FlowAdapter(listFlow);
                rcvFlow.setAdapter(flowAdapter);
            }

            @Override
            public void onFailure(Call<List<Flow>> call, Throwable t) {
                Toast.makeText(FlowActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}