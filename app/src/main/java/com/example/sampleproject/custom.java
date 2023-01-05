package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sampleproject.helper.DBTimeTableHelper;

public class custom extends AppCompatActivity {
    DBTimeTableHelper dbTimeTableHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        dbTimeTableHelper = new DBTimeTableHelper(this);
        Spinner spinner_day =  findViewById(R.id.spinner_date);
        Spinner spinner_start = findViewById(R.id.spinner_start);
        Spinner spinner_end =  findViewById(R.id.spinner_end);

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

        EditText subject = findViewById(R.id.et_subject);
        Spinner day_spinner = findViewById(R.id.spinner_date);
        Spinner start_spinner = findViewById(R.id.spinner_start);
        Spinner end_spinner = findViewById(R.id.spinner_end);

        // Apply the adapter to the spinner
        spinner_day.setAdapter(adapter_day);
        spinner_start.setAdapter(adapter_start);
        spinner_end.setAdapter(adapter_end);
        Button insert = findViewById(R.id.btn_insert);

//        cursor = dbTimeTableHelper.getData("SELECT * FROM time_table WHERE day ="+"'Tuesday'");
//        while (cursor.moveToNext()) {
//            String str = cursor.getString(0);
//            String str1 = cursor.getString(1);
//            String str2 = cursor.getString(2);
//            String str3 = cursor.getString(3);
////                    humilist.add(Double.parseDouble(str1));
////                    templist.add(Double.parseDouble(str));
////                    pressurelist.add(Double.parseDouble(str2));
//
//    Toast.makeText(custom.this, str + " " + str1 + " " + str2, Toast.LENGTH_LONG).show();
//        }
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject_text =  subject.getText().toString();
                String day_text =  day_spinner.getSelectedItem().toString();
                String start_text =  start_spinner.getSelectedItem().toString();
                String end_text =  end_spinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(subject_text) || TextUtils.isEmpty(day_text) || TextUtils.isEmpty(start_text) || TextUtils.isEmpty(end_text))
                    Toast.makeText(custom.this, "All fields is required", Toast.LENGTH_SHORT).show();
                else {
                    SQLiteDatabase sqLiteDatabase = null;
//                    dbTimeTableHelper.onUpgrade(sqLiteDatabase,1,5);
                    Toast.makeText(custom.this, "Insert successfull", Toast.LENGTH_SHORT).show();

                    dbTimeTableHelper.insertData(day_text, subject_text, start_text, end_text);
                    Intent back = new Intent(custom.this, TimeTableActivity.class);
                    startActivity(back);
                }
            }
        });
    }
}