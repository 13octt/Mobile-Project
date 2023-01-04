package com.example.sampleproject;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject.helper.DBTimeTableHelper;

public class TimeTableActivity extends AppCompatActivity {

    DBTimeTableHelper dbTimeTableHelper ;

    SQLiteDatabase sqLiteDatabase;
    Button addSubject, deleteSubject;
    TableLayout tableLayout;
    public String edit;
    public TextView etMon1;
//    EditText usrname, day , period_begin,period_end,subject,password, repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        dbTimeTableHelper = new DBTimeTableHelper(this);
        TextView tt = (TextView) findViewById(R.id.txt_time_table);
        //button add
        addSubject = (Button) findViewById(R.id.button_add) ;


        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(TimeTableActivity.this);
                View customDialogView = layoutInflater.inflate(R.layout.custom_dialog, null);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TimeTableActivity.this);
                alertDialog.setView(customDialogView);
                Spinner spinner = (Spinner) findViewById(R.id.spinner_start);
// Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                        R.array.day, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
                spinner.setAdapter(adapter);
//                Spinner spinner = (Spinner) findViewById(R.id.spinner);
//                // Create an ArrayAdapter using the string array and a default spinner layout
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(TimeTableActivity.this, R.array.planets_array, android.R.layout.simple_spinner_item);
//                // Specify the layout to use when the list of choices appears
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                // Apply the adapter to the spinner
//                spinner.setAdapter(adapter);

//                etMon1 = (TextView) findViewById(R.id.et_name);
//                final EditText usrname = (EditText) customDialogView.findViewById(R.id.et_name);
//                final EditText day = (EditText) customDialogView.findViewById(R.id.et_day);
//                final EditText period_begin = (EditText) customDialogView.findViewById(R.id.et_start_period);
//                final EditText period_end = (EditText) customDialogView.findViewById(R.id.et_end_period);
//                final EditText subject = (EditText) customDialogView.findViewById(R.id.et_subject);

                alertDialog.setCancelable(false)
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {


//                        String usr = usrname.getText().toString().trim();
//                        String date = day.getText().toString().trim();
//                        String sub = subject.getText().toString().trim();
//                        Integer begin = Integer.parseInt(period_begin.getText().toString());
//                        Integer end = Integer.parseInt(period_end.getText().toString());
//                        if (TextUtils.isEmpty(sub) || TextUtils.isEmpty(date) || TextUtils.isEmpty(usr) || TextUtils.isEmpty(begin.toString()) || TextUtils.isEmpty(end.toString()))
//                            Toast.makeText(TimeTableActivity.this, "All fields is required", Toast.LENGTH_SHORT).show();
//                        else {
////                            dbTimeTableHelper.insert("math");
//                          dbTimeTableHelper.insertData(usr,date, sub, begin, end);
//                                     Toast.makeText(TimeTableActivity.this,usr,Toast.LENGTH_SHORT).show();
//
//                        }
                    }
                });
                alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialog.create();
                alertDialog.show();
            }
        });



    }
}