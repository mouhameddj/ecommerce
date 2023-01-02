package com.example.e_commerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public String x;
    private static final String DATABASE_NAME = "tayara.db";
    public DbHelper(Context context) {
        super(context, "tayara.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT primary key, pwd TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  users");
    }

    public Boolean insertdata(String username, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("pwd", pwd);
        long result =db.insert("users",null,contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public Boolean checkusername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ?",new String[]{username});

        if (cursor.getCount()>0){

            return true;
        }else {
            return false;
        }
    }
    public Boolean checkusernamepassword(String username,String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ? and pwd = ?",new String[]{username,pwd});

        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }


}