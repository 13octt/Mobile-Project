package com.example.sampleproject.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBUserHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;
    public DBUserHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users ");
    }

    public Boolean insertData(String user, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", user);
        values.put("password", pass);
        long result = db.insert("users", null, values);
        if (result == 1)
            return false;
        else
            return true;
    }

    public Boolean checkUserName (String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String []{username});
        if(cursor.getCount() > 0){
            return true;
        }
        else
            return false;
    }

    public Boolean checkUserPass (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?" , new String []{username,password});
        if(cursor.getCount() > 0){
            return true;
        }
        else
            return false;
    }
}
