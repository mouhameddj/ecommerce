package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    ImageButton btn_add, btn_logout;
    private RecyclerView rv;
    private DBhelper1 DB1;
    public String user;
    // private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // actionBar = getSupportActionBar();
        //   actionBar.setTitle("All Products");
        btn_add = (ImageButton) findViewById(R.id.btn_add);
        btn_logout = (ImageButton) findViewById(R.id.btn_logout);
        rv = (RecyclerView) findViewById(R.id.rv);
        DB1 = new DBhelper1(this);

        Intent intent1 = getIntent();
        user = intent1.getStringExtra("user");
        Intent intent2 = getIntent();
        user = intent2.getStringExtra("user");
        LoadRecords1(user);
        Intent intent3 = getIntent();
        user = intent3.getStringExtra("user");
        LoadRecords1(user);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, add_product.class);

                intent.putExtra("IsEditMode", false);
                intent.putExtra("user", user);
                startActivity(intent);


            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, recyclerview1.class);
                startActivity(intent);
            }
        });
    }

    private void LoadRecords1(String user) {

        AdapterRecord adapterrecord = new AdapterRecord(HomeActivity.this, DB1.getallrecords1(user));

        rv.setAdapter(adapterrecord);
        // actionBar.setSubtitle("Total : " + DB1.getcount());
        rv.setLayoutManager(new LinearLayoutManager(HomeActivity.this));


    }


    @Override
    protected void onResume() {
        super.onResume();
        LoadRecords1(user);
    }


}