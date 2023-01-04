package com.example.sampleproject;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject.helper.DBTimeTableHelper;

public class TimeTableActivity extends AppCompatActivity {

    DBTimeTableHelper dbTimeTableHelper ;
    Cursor cursor;

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
//        TextView tt = (TextView) findViewById(R.id.txt_time_table);
//        //button add
//        addSubject = (Button) findViewById(R.id.button_add) ;
    Button btn_mon = (Button) findViewById(R.id.mondaybtn);
    Button btn_tue = (Button) findViewById(R.id.tuesdaybtn);
    Button btn_wed = (Button) findViewById(R.id.wednesdaybtn);
    Button btn_thur = (Button) findViewById(R.id.thursday);
    Button btn_fri = (Button) findViewById(R.id.fridaybtn);
    Button btn_sat = (Button) findViewById(R.id.saturday);
    click_listener(btn_mon,"Monday");
    click_listener(btn_tue,"Tuesday");
    click_listener(btn_wed,"Wednesday");
    click_listener(btn_thur,"Thursday");
    click_listener(btn_fri,"Friday");
    click_listener(btn_sat,"Saturday");



    }
    public void click_listener(Button button, String string){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView name = (TextView) findViewById(R.id.usroles_name);
                TextView name1 = (TextView) findViewById(R.id.usroles_name2);
                TextView sub = (TextView) findViewById(R.id.usroles_id);
                TextView sub1 = (TextView) findViewById(R.id.usroles_id2);
                TextView begin = (TextView) findViewById(R.id.usroles_description);
                TextView begin1 = (TextView) findViewById(R.id.usroles_description2);
                TextView end = (TextView) findViewById(R.id.usroles_composite);
                TextView end1 = (TextView) findViewById(R.id.usroles_composite2);

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.class1);
                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.class2);
                int i = 0;
                cursor = dbTimeTableHelper.getData("SELECT * FROM time_table WHERE day =" + "'" + string + "'");
                while (cursor.moveToNext()) {
                    String str = cursor.getString(0);
                    String str1 = cursor.getString(1);
                    String str2 = cursor.getString(2);
                    String str3 = cursor.getString(3);
                    if (i == 0) {
                        name.setText(str);
                        sub.setText(str1);
                        begin.setText(str2);
                        end.setText(str3);
                        i++;
                        Toast.makeText(TimeTableActivity.this, "done", Toast.LENGTH_SHORT).show();
                        relativeLayout.setBackgroundColor(Color.rgb(30,42,65));
                    }
                    if (i == 1) {
                        name1.setText(str);
                        sub1.setText(str1);
                        begin1.setText(str2);
                        end1.setText(str3);
                        relativeLayout.setBackgroundColor(Color.rgb(30,42,65));

                        i++;

                    } else {
                        i = 0;
                    }

                }
            }
        });
    }
}