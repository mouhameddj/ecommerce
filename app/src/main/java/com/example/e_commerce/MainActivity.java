package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,pwd,repwd;
    Button signin,signup;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        username=(EditText) findViewById(R.id.username);
        pwd=(EditText) findViewById(R.id.pwd);
        repwd=(EditText) findViewById(R.id.repwd);
        signin=(Button) findViewById(R.id.signin);
        signup=(Button) findViewById(R.id.signup);
        DB = new DbHelper(this);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user =username.getText().toString();
                String password =pwd.getText().toString();
                String repass =repwd.getText().toString();
                if (user.equals("")||password.equals("")||repass.equals("")){
                    Toast.makeText(MainActivity.this,"please enter all the fields ",Toast.LENGTH_LONG);
                }else {
                    if(password.equals(repass)){
                        Boolean checkuser =DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertdata(user,password);
                            if (insert==true){
                                Toast.makeText(MainActivity.this,"registred successfully ",Toast.LENGTH_LONG);
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"registred failed ",Toast.LENGTH_SHORT);

                            }
                        }else{
                            Toast.makeText(MainActivity.this,"User Already exists ",Toast.LENGTH_LONG);
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Password not matching ",Toast.LENGTH_LONG);
                    }
                }

            }
        });
    }

}