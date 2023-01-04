package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class custom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        Spinner spinner_day = (Spinner) findViewById(R.id.spinner_date);
        Spinner spinner_start = (Spinner) findViewById(R.id.spinner_start);
        Spinner spinner_end = (Spinner) findViewById(R.id.spinner_end);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_day = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.day, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter_start = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.start, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_end = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.start, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_start.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_end.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinner_day.setAdapter(adapter_day);
        spinner_start.setAdapter(adapter_start);
        spinner_end.setAdapter(adapter_end);

    }
}