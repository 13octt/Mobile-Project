package com.example.sampleproject.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBGraphHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "graph.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_GRAPH = "graph";

    private static final String KEY_ID = "id";
    private static final String KEY_TEMP = "tempurate";
    private static final String KEY_HUMIDITY = "humidity";
    private static final String KEY_PRESSURE = "pressure";
    private static final String KEY_TEMP_MAX = "temp_max";
    private static final String KEY_TEMP_MIN = "temp_min";
    private static final String KEY_SEA_LEVEL = "sea_level";


    public DBGraphHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqLite = "CREATE TABLE " + TABLE_GRAPH + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TEMP + " TEXT,"
                + KEY_HUMIDITY + " TEXT,"
                + KEY_PRESSURE + " TEXT,"
                + KEY_TEMP_MAX + " TEXT,"
                + KEY_TEMP_MIN + " TEXT,"
                + KEY_SEA_LEVEL + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(sqLite);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GRAPH);
        //
        onCreate(sqLiteDatabase);
    }

    public Boolean insertData(String id, String ver, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("version", ver);
        values.put("name", name);
        long results = db.insert("graph", null, values);
        if (results == 1)
            return true;
        else
            return false;
    }


//    public Boolean checkGraph(String abc, ...){}


}
