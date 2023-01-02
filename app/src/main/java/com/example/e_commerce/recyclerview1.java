package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class recyclerview1 extends AppCompatActivity {
    private RecyclerView rv1;
    private DBhelper1 DB1;
    private ImageButton btnlog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview1);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        btnlog=(ImageButton) findViewById(R.id.btnlog);
        rv1 = (RecyclerView) findViewById(R.id.rv2);



        DB1 = new DBhelper1(this);
        LoadRecords();
btnlog.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


             Intent intent = new Intent(recyclerview1.this,MainActivity.class);
         startActivity(intent);}
});

    }

    private void LoadRecords() {
        AdapterRecord1 adapterrecord1 = new AdapterRecord1(recyclerview1.this, DB1.getallrecords());

        rv1.setAdapter(adapterrecord1);
        // actionBar.setSubtitle("Total : " + DB1.getcount());
        rv1.setLayoutManager(new LinearLayoutManager(recyclerview1.this));
        rv1.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }
    @Override
    protected void onResume() {
        super.onResume();
        LoadRecords();
    }
}