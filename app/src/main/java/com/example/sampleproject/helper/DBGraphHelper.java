package com.example.sampleproject.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;

public class DBGraphHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "graph.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_GRAPH = "graph";
    private static final String KEY_ID = "id";
    public static final String KEY_TEMP = "tempurate";
    public static final String KEY_HUMIDITY = "humidity";
    public static final String KEY_PRESSURE = "pressure";
    private static final String KEY_TEMP_MAX = "temp_max";
    private static final String KEY_TEMP_MIN = "temp_min";
    private static final String KEY_SEA_LEVEL = "sea_level";
    DBGraphHelper dbHelper;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabase ;
    public DBGraphHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqLite = "CREATE TABLE " + TABLE_GRAPH + "("
                + KEY_TEMP + " TEXT,"
                + KEY_HUMIDITY + " TEXT,"
                + KEY_PRESSURE + " TEXT "
//                + KEY_TEMP_MAX + " TEXT,"
//                + KEY_TEMP_MIN + " TEXT,"
//                + KEY_SEA_LEVEL + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(sqLite);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GRAPH);
        onCreate(sqLiteDatabase);
    }

    public void insertData(Double temp, Double humidity, Double pressure) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TEMP, temp);
        values.put(KEY_HUMIDITY, humidity);
        values.put(KEY_PRESSURE, pressure);
//        long results =
        db.insert(TABLE_GRAPH, null, values);
        db.close();

//        if (results == 1)
//            return true;
//        else
//            return false;
    }

    public Boolean checkHumidity(Double humidity){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM graph WHERE humidity = ?", new String []{humidity.toString()});
        if(cursor.getCount() > 0){
            return true;
        }
        else
            return false;
    }

    public Boolean checkTemp(Double temp){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM graph WHERE tempurature = ?", new String []{temp.toString()});
        if(cursor.getCount() > 0){
            return true;
        }
        else
            return false;
    }

    public Boolean checkPressure(Double pressure){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM graph WHERE pressure = ?", new String []{pressure.toString()});
        if(cursor.getCount() > 0){
            return true;
        }
        else
            return false;
    }
    public Cursor getAllData(){
        sqLiteDatabase = dbHelper.getWritableDatabase();

        return sqLiteDatabase.query(DATABASE_NAME, new String[]{KEY_HUMIDITY, KEY_TEMP, KEY_PRESSURE}, null, null, null, null, null);

    }
    public Cursor getColumnData(String name){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase.query(DATABASE_NAME,new  String[]{name}, null, null, null, null, null);

    }


    public Cursor getData(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery(sql, null);
        return cursor;
    }



}
