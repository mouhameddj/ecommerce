package com.example.e_commerce;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class RecorddetailsActivity extends AppCompatActivity {
    ImageView add_photo;
    TextView titletv,categorytv,descreptiontv,prixtv,teltv,usertv;
    ActionBar actionBar;
    private DBhelper1 DB1;
    private String recordID;
    ImageButton btn_tel,btn_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorddetails);

        actionBar = getSupportActionBar();
        actionBar.setTitle("PRODUCT DETAILS");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        Intent intent =getIntent();
         recordID = intent.getStringExtra("RECORD_ID");
        titletv=(TextView)findViewById(R.id.titletv);
        categorytv=(TextView)findViewById(R.id.categorytv);
        usertv=(TextView)findViewById(R.id.usertv);
        descreptiontv=(TextView)findViewById(R.id.descreptiontv);
        prixtv=(TextView)findViewById(R.id.prixtv);
        teltv=(TextView)findViewById(R.id.teltv);
        btn_msg=(ImageButton) findViewById(R.id.btn_msg);
        btn_tel=(ImageButton) findViewById(R.id.btn_tel);
        add_photo=(ImageView) findViewById(R.id.imageView);
        DB1=new DBhelper1(this);
        showRecordDetails();
        btn_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String telp = teltv.getText().toString();
                Toast.makeText(RecorddetailsActivity.this, "tel"+telp, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telp, null));
                startActivity(intent);

            }

        });
        btn_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telp = teltv.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", telp, null));

                startActivity(intent);

            }
        });


    }

    private void showRecordDetails() {
        String selectquery = "SELECT * FROM " + DB1.TABLE_NAME + " WHERE " + DB1.COLUMN_ID +" =\"" + recordID+"\"";
        SQLiteDatabase db = DB1.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectquery,null);
        if (cursor.moveToFirst()) {
            do{

                      String id =  ""+cursor.getInt(cursor.getColumnIndexOrThrow(DB1.COLUMN_ID));
                      String img1= ""+cursor.getString(cursor.getColumnIndexOrThrow(DB1.COLUMN_IMAGE));
                      String Title= ""+cursor.getString(cursor.getColumnIndexOrThrow(DB1.COLUMN_TITLE));
                      String Descreption= ""+cursor.getString(cursor.getColumnIndexOrThrow(DB1.COLUMN_DESCRIPTION));
                      String Category=""+cursor.getString(cursor.getColumnIndexOrThrow(DB1.COLUMN_CATEGORY));
                      String price=""+cursor.getString(cursor.getColumnIndexOrThrow(DB1.COLUMN_PRICE));
                      String tel=""+cursor.getString(cursor.getColumnIndexOrThrow(DB1.COLUMN_TEL));
                String user=""+cursor.getString(cursor.getColumnIndexOrThrow(DB1.COLUMN_VENDEUR));

                if (img1.equals("null"))
                {
                   add_photo.setImageResource(R.drawable.ic_baseline_add_a_photo_24);

                }else{
                   add_photo.setImageURI(Uri.parse(img1));
                }

                titletv.setText(Title);
                descreptiontv.setText(Descreption);
                categorytv.setText(Category);
                prixtv.setText(price);
                teltv.setText(tel);
                usertv.setText(user);

            }
            while (cursor.moveToNext());

        }
        db.close();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}