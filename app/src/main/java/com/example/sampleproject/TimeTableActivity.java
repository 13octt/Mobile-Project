package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import org.w3c.dom.Text;

public class TimeTableActivity extends AppCompatActivity {

    DBTimeTableHelper dbTimeTableHelper;
    SQLiteDatabase sqLiteDatabase;
    Button addSubject, deleteSubject;
    TableLayout tableLayout;
    public String edit;
    public TextView etMon1;
//    EditText usrname, day , period_begin,period_end,subject,password, repassword;
    TextView mon1, tue1, wed1, thu1, fri1, sat1, sun1,
            mon2, tue2, wed2, thu2, fri2, sat2, sun2,
            mon3, tue3, wed3, thu3, fri3, sat3, sun3,
            mon4, tue4, wed4, thu4, fri4, sat4, sun4,
            mon5, tue5, wed5, thu5, fri5, sat5, sun5,
            mon6, tue6, wed6, thu6, fri6, sat6, sun6,
            mon7, tue7, wed7, thu7, fri7, sat7, sun7,
            mon8, tue8, wed8, thu8, fri8, sat8, sun8,
            mon9, tue9, wed9, thu9, fri9, sat9, sun9,
            mon10, tue10, wed10, thu10, fri10, sat10, sun10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        TextView tt = (TextView) findViewById(R.id.txt_time_table);
        //button add
        addSubject = (Button) findViewById(R.id.button_add) ;
        //Mon
        mon1 = (TextView) findViewById(R.id.mon_1);
        mon2 = (TextView) findViewById(R.id.mon_2);
        mon3 = (TextView) findViewById(R.id.mon_3);
        mon4 = (TextView) findViewById(R.id.mon_4);
        mon5 = (TextView) findViewById(R.id.mon_5);
        mon6 = (TextView) findViewById(R.id.mon_6);
        mon7 = (TextView) findViewById(R.id.mon_7);
        mon8 = (TextView) findViewById(R.id.mon_8);
        mon9 = (TextView) findViewById(R.id.mon_9);
        mon10 = (TextView) findViewById(R.id.mon_10);

        String mo1 = mon1.getText().toString().trim();
        String mo2 = mon2.getText().toString().trim();
        String mo3 = mon3.getText().toString().trim();
        String mo4 = mon4.getText().toString().trim();
        String mo5 = mon5.getText().toString().trim();
        String mo6 = mon6.getText().toString().trim();
        String mo7 = mon7.getText().toString().trim();
        String mo8 = mon8.getText().toString().trim();
        String mo9 = mon9.getText().toString().trim();
        String mo10 = mon10.getText().toString().trim();

        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(TimeTableActivity.this);
                View customDialogView = layoutInflater.inflate(R.layout.custom_dialog, null);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TimeTableActivity.this);
                alertDialog.setView(customDialogView);

//                Spinner spinner = (Spinner) findViewById(R.id.spinner);
//                // Create an ArrayAdapter using the string array and a default spinner layout
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(TimeTableActivity.this, R.array.planets_array, android.R.layout.simple_spinner_item);
//                // Specify the layout to use when the list of choices appears
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                // Apply the adapter to the spinner
//                spinner.setAdapter(adapter);

//                etMon1 = (TextView) findViewById(R.id.et_name);


                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        final EditText usrname = (EditText) customDialogView.findViewById(R.id.et_name);
                        final EditText day = (EditText) customDialogView.findViewById(R.id.et_day);
                        final EditText period_begin = (EditText) customDialogView.findViewById(R.id.et_start_period);
                        final EditText period_end = (EditText) customDialogView.findViewById(R.id.et_end_period);
                        final EditText subject = (EditText) customDialogView.findViewById(R.id.et_subject);

                        String usr = usrname.getText().toString().trim();
                        String date = day.getText().toString().trim();
                        String sub = subject.getText().toString().trim();
                        Integer begin = Integer.parseInt(period_begin.getText().toString());
                        Integer end = Integer.parseInt(period_end.getText().toString());

                        if (TextUtils.isEmpty(sub) || TextUtils.isEmpty(date) || TextUtils.isEmpty(usr) || TextUtils.isEmpty(begin.toString()) || TextUtils.isEmpty(end.toString()))
                            Toast.makeText(TimeTableActivity.this, "All fields is required", Toast.LENGTH_SHORT).show();
                        else {
                           dbTimeTableHelper.insertData(usr, date, sub, begin, end);
////                                     Toast.makeText(TimeTableActivity.this,usr,Toast.LENGTH_SHORT).show();
                            Log.e("insert",usr);
                            Log.e("insert",date);
                            Log.e("insert",sub);
                            Log.e("insert",begin.toString());
                            Log.e("insert",end.toString());
                        }
                    }
                });
                alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });

        //Tue
        tue1 = (TextView) findViewById(R.id.tue_1);
        tue2 = (TextView) findViewById(R.id.tue_2);
        tue3 = (TextView) findViewById(R.id.tue_3);
        tue4 = (TextView) findViewById(R.id.tue_4);
        tue5 = (TextView) findViewById(R.id.tue_5);
        tue6 = (TextView) findViewById(R.id.tue_6);
        tue7 = (TextView) findViewById(R.id.tue_7);
        tue8 = (TextView) findViewById(R.id.tue_8);
        tue9 = (TextView) findViewById(R.id.tue_9);
        tue10 = (TextView) findViewById(R.id.tue_10);

        String tu1 = tue1.getText().toString().trim();
        String tu2 = tue2.getText().toString().trim();
        String tu3 = tue3.getText().toString().trim();
        String tu4 = tue4.getText().toString().trim();
        String tu5 = tue5.getText().toString().trim();
        String tu6 = tue6.getText().toString().trim();
        String tu7 = tue7.getText().toString().trim();
        String tu8 = tue8.getText().toString().trim();
        String tu9 = tue9.getText().toString().trim();
        String tu10 = tue10.getText().toString().trim();

        //Wed
        wed1 = (TextView) findViewById(R.id.wed_1);
        wed2 = (TextView) findViewById(R.id.wed_2);
        wed3 = (TextView) findViewById(R.id.wed_3);
        wed4 = (TextView) findViewById(R.id.wed_4);
        wed5 = (TextView) findViewById(R.id.wed_5);
        wed6 = (TextView) findViewById(R.id.wed_6);
        wed7 = (TextView) findViewById(R.id.wed_7);
        wed8 = (TextView) findViewById(R.id.wed_8);
        wed9 = (TextView) findViewById(R.id.wed_9);
        wed10 = (TextView) findViewById(R.id.wed_10);

        String we1 = wed1.getText().toString().trim();
        String we2 = wed2.getText().toString().trim();
        String we3 = wed3.getText().toString().trim();
        String we4 = wed4.getText().toString().trim();
        String we5 = wed5.getText().toString().trim();
        String we6 = wed6.getText().toString().trim();
        String we7 = wed7.getText().toString().trim();
        String we8 = wed8.getText().toString().trim();
        String we9 = wed9.getText().toString().trim();
        String we10 = wed10.getText().toString().trim();

        //Thu
        thu1 = (TextView) findViewById(R.id.thu_1);
        thu2 = (TextView) findViewById(R.id.thu_2);
        thu3 = (TextView) findViewById(R.id.thu_3);
        thu4 = (TextView) findViewById(R.id.thu_4);
        thu5 = (TextView) findViewById(R.id.thu_5);
        thu6 = (TextView) findViewById(R.id.thu_6);
        thu7 = (TextView) findViewById(R.id.thu_7);
        thu8 = (TextView) findViewById(R.id.thu_8);
        thu9 = (TextView) findViewById(R.id.thu_9);
        thu10 = (TextView) findViewById(R.id.thu_10);

        String th1 = thu1.getText().toString().trim();
        String th2 = thu2.getText().toString().trim();
        String th3 = thu3.getText().toString().trim();
        String th4 = thu4.getText().toString().trim();
        String th5 = thu5.getText().toString().trim();
        String th6 = thu6.getText().toString().trim();
        String th7 = thu7.getText().toString().trim();
        String th8 = thu8.getText().toString().trim();
        String th9 = thu9.getText().toString().trim();
        String th10 = thu10.getText().toString().trim();

        //Fri
        fri1 = (TextView) findViewById(R.id.fri_1);
        fri2 = (TextView) findViewById(R.id.fri_2);
        fri3 = (TextView) findViewById(R.id.fri_3);
        fri4 = (TextView) findViewById(R.id.fri_4);
        fri5 = (TextView) findViewById(R.id.fri_5);
        fri6 = (TextView) findViewById(R.id.fri_6);
        fri7 = (TextView) findViewById(R.id.fri_7);
        fri8 = (TextView) findViewById(R.id.fri_8);
        fri9 = (TextView) findViewById(R.id.fri_9);
        fri10 = (TextView) findViewById(R.id.fri_10);

        String fr1 = fri1.getText().toString().trim();
        String fr2 = fri2.getText().toString().trim();
        String fr3 = fri3.getText().toString().trim();
        String fr4 = fri4.getText().toString().trim();
        String fr5 = fri5.getText().toString().trim();
        String fr6 = fri6.getText().toString().trim();
        String fr7 = fri7.getText().toString().trim();
        String fr8 = fri8.getText().toString().trim();
        String fr9 = fri9.getText().toString().trim();
        String fr10 = fri10.getText().toString().trim();

        //Sat
        sat1 = (TextView) findViewById(R.id.sat_1);
        sat2 = (TextView) findViewById(R.id.sat_2);
        sat3 = (TextView) findViewById(R.id.sat_3);
        sat4 = (TextView) findViewById(R.id.sat_4);
        sat5 = (TextView) findViewById(R.id.sat_5);
        sat6 = (TextView) findViewById(R.id.sat_6);
        sat7 = (TextView) findViewById(R.id.sat_7);
        sat8 = (TextView) findViewById(R.id.sat_8);
        sat9 = (TextView) findViewById(R.id.sat_9);
        sat10 = (TextView) findViewById(R.id.sat_10);

        String sa1 = sat1.getText().toString().trim();
        String sa2 = sat2.getText().toString().trim();
        String sa3 = sat3.getText().toString().trim();
        String sa4 = sat4.getText().toString().trim();
        String sa5 = sat5.getText().toString().trim();
        String sa6 = sat6.getText().toString().trim();
        String sa7 = sat7.getText().toString().trim();
        String sa8 = sat8.getText().toString().trim();
        String sa9 = sat9.getText().toString().trim();
        String sa10 = sat10.getText().toString().trim();

        //Sun
        sun1 = (TextView) findViewById(R.id.sun_1);
        sun2 = (TextView) findViewById(R.id.sun_2);
        sun3 = (TextView) findViewById(R.id.sun_3);
        sun4 = (TextView) findViewById(R.id.sun_4);
        sun5 = (TextView) findViewById(R.id.sun_5);
        sun6 = (TextView) findViewById(R.id.sun_6);
        sun7 = (TextView) findViewById(R.id.sun_7);
        sun8 = (TextView) findViewById(R.id.sun_8);
        sun9 = (TextView) findViewById(R.id.sun_9);
        sun10 = (TextView) findViewById(R.id.sun_10);

        String su1 = sun1.getText().toString().trim();
        String su2 = sun2.getText().toString().trim();
        String su3 = sun3.getText().toString().trim();
        String su4 = sun4.getText().toString().trim();
        String su5 = sun5.getText().toString().trim();
        String su6 = sun6.getText().toString().trim();
        String su7 = sun7.getText().toString().trim();
        String su8 = sun8.getText().toString().trim();
        String su9 = sun9.getText().toString().trim();
        String su10 = sun10.getText().toString().trim();


    }
}