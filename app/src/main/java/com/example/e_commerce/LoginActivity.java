package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username,pwd;
    public String z;
    Button login;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        username=(EditText) findViewById(R.id.username1);
        pwd=(EditText) findViewById(R.id.pwd1);
        login=(Button) findViewById(R.id.signup1);
        DB = new DbHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user =username.getText().toString();
                String password =pwd.getText().toString();
                if (user.equals("")||password.equals("")){
                    Toast.makeText(LoginActivity.this,"please enter all the fields ",Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkuserpass = DB.checkusernamepassword(user,password);
                    if (checkuserpass==true){
                        z=user;
                        Toast.makeText(LoginActivity.this,"sign in with success ",Toast.LENGTH_LONG).show();

                        Intent intent1 = new Intent(LoginActivity.this,HomeActivity.class);
                        intent1.putExtra("user",user);
                        startActivity(intent1);

                    }else {
                        Toast.makeText(LoginActivity.this,"login failed ",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}