package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.sampleproject.Adapter.ValueDescriptorAdapter;
import com.example.sampleproject.Model.ValueDescriptor;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValueDescriptorActivity extends AppCompatActivity {

    private RecyclerView rcvValueDescriptor;
    private List<ValueDescriptor> listValueDescriptor;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_descriptor);

        Button btnBack = (Button) findViewById(R.id.btn_back_from_value_descriptor);
        btnBack.setOnClickListener(view -> {
            Intent back = new Intent(ValueDescriptorActivity.this, MainActivity.class);
            startActivity(back);
        });

        rcvValueDescriptor = findViewById(R.id.rcv_value_descriptor);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvValueDescriptor.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvValueDescriptor.addItemDecoration(dividerItemDecoration);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        callApiValueDescriptor();
    }

    private void callApiValueDescriptor(){
        apiInterface.getValueDescriptor().enqueue(new Callback<List<ValueDescriptor>>() {
            @Override
            public void onResponse(Call<List<ValueDescriptor>> call, Response<List<ValueDescriptor>> response) {
                listValueDescriptor = new ArrayList<>(response.body());
                ValueDescriptorAdapter valueDescriptorAdapter = new ValueDescriptorAdapter(listValueDescriptor);
                rcvValueDescriptor.setAdapter(valueDescriptorAdapter);

            }

            @Override
            public void onFailure(Call<List<ValueDescriptor>> call, Throwable t) {
                Toast.makeText(ValueDescriptorActivity.this, "ON FAILURE", Toast.LENGTH_SHORT).show();
            }
        });
    }

}