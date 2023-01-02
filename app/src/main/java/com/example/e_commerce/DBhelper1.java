package com.example.e_commerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBhelper1 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tayar.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "my_product";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IMAGE = "product_img";
    public static final String COLUMN_TITLE = "product_title";
    public static final String COLUMN_PRICE = "product_price";
    public static final String COLUMN_CATEGORY = "product_category";
    public static final String COLUMN_DESCRIPTION = "product_description";
    public static final String COLUMN_VENDEUR = "product_vendeur";


    public static final String COLUMN_TEL = "product_tel";

    public DBhelper1(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IMAGE + " TEXT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_VENDEUR + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_TEL + " TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertdata(String image, String title, String price, String category, String description, String tel,String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IMAGE, image);
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_PRICE, price);
        contentValues.put(COLUMN_CATEGORY, category);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_TEL, tel);
        contentValues.put(COLUMN_VENDEUR, user);


        long id = db.insert(TABLE_NAME, null, contentValues);
        return id;


    }




    public void updatedata(String id, String image, String title, String price, String category, String description, String tel,String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IMAGE, image);
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_PRICE, price);
        contentValues.put(COLUMN_CATEGORY, category);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_TEL, tel);
        contentValues.put(COLUMN_VENDEUR, user);


db.update(TABLE_NAME, contentValues, COLUMN_ID +" = ?", new String []{id});



    }


    public ArrayList<Modelrecord> getallrecords1(String user) {
        ArrayList<Modelrecord> recordList = new ArrayList<>();
        String selectquery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_VENDEUR +" =\"" + user+"\"";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectquery,null);
        if (cursor.moveToFirst()) {
            do{
                Modelrecord modeLRecord = new Modelrecord(
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEL)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VENDEUR))
                );
                recordList.add(modeLRecord);

            }
            while (cursor.moveToNext());

        }
        db.close();
        return recordList;

    }

    public ArrayList<Modelrecord> getallrecords() {
        ArrayList<Modelrecord> recordList = new ArrayList<>();
        String selectquery = "SELECT * FROM " + TABLE_NAME  ;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectquery,null);
        if (cursor.moveToFirst()) {
            do{
                Modelrecord modeLRecord = new Modelrecord(
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEL)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VENDEUR))
                );
                recordList.add(modeLRecord);

            }
            while (cursor.moveToNext());

        }
        db.close();
        return recordList;

    }

    public void deletedata(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID +" = ?", new String []{id});
        db.close();
    }
    public int getcount() {
        ArrayList<Modelrecord> recordList = new ArrayList<>();
        String Countquery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(Countquery,null);
       int count=cursor.getCount();
       cursor.close();

        return count;

    }
}