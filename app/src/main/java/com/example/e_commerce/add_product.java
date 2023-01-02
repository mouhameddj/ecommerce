package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class add_product extends AppCompatActivity {





    TextView user1;
    EditText title,category,descreption,tel,prix;
    ImageView addphoto;
    FloatingActionButton add1;
    ActionBar actionBar;
    DBhelper1 db1;
    DbHelper DB;
    SQLiteDatabase sqLiteDatabase;

    private static final int CAMERA_REQUEST= 100;
    private static final int STORAGE_REQUEST = 101;
    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 102;
    private String[] cameraPermissions;
    private String[] storagePermissions;
    private Uri imageUri;
    private String id,titles,categorys,descreptions,tels,price;
    boolean IsEditMode = false;
    int SELECT_PICTURE = 200;
    private String users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        DB = new DbHelper(this);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Add Product");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        prix=(EditText) findViewById(R.id.prix);
        title=(EditText) findViewById(R.id.title);
        category=(EditText) findViewById(R.id.category);
        descreption=(EditText) findViewById(R.id.descreptiontv);
        tel=(EditText) findViewById(R.id.tel);
        user1=(TextView) findViewById(R.id.usertv);

        addphoto=(ImageView) findViewById(R.id.add_photo);
        add1=(FloatingActionButton) findViewById(R.id.btn_addpr);
        Intent intent =getIntent();
        IsEditMode =intent.getBooleanExtra("IsEditMode",false);
        users = intent.getStringExtra("user");
        user1.setText(users);
        if(IsEditMode){


            actionBar.setTitle("update product");
            id = intent.getStringExtra("ID");

            imageUri =  Uri.parse(intent.getStringExtra("IMG"));
            titles = intent.getStringExtra("TITLE");
            descreptions = intent.getStringExtra("DESCR");
            categorys = intent.getStringExtra("CATEG");
            tels = intent.getStringExtra("TEL");
            price = intent.getStringExtra("PRIX");
            users = intent.getStringExtra("USER");
            user1.setText(users);
            title.setText(titles);
            category.setText(categorys);
            descreption.setText(descreptions);

            tel.setText(tels);
            prix.setText(price);

            if (imageUri.toString().equals("null"))
            {
                addphoto.setImageResource(R.drawable.ic_baseline_add_a_photo_24);

            }else{
                addphoto.setImageURI(imageUri);
            }


        }
        else
        {
            actionBar.setTitle("add product");


        }

        cameraPermissions = new String[] {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        db1 = new DBhelper1(this);

        addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imagepickdialog();




                }


        });
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputdata();

            }
        });
    }

    private void inputdata() {
        Intent intent =getIntent();


        users = user1.getText().toString().trim();
        titles =""+title.getText().toString().trim();

        categorys = ""+category.getText().toString().trim();
        descreptions= ""+descreption.getText().toString().trim();
        price=""+prix.getText().toString().trim();
        tels=""+tel.getText().toString().trim();

        if (IsEditMode){
                db1.updatedata(
                        ""+id,
                    ""+imageUri,
                    ""+titles,
                    ""+price,
                    ""+categorys,
                    ""+descreptions,
                    ""+tels,
                        ""+users


            );


            Toast.makeText(add_product.this,"updated ....",Toast.LENGTH_LONG).show();
            Intent intent3 = new Intent(add_product.this, HomeActivity.class);
            intent3.putExtra("user",users);
            startActivity(intent3);


        }
        else {
            long id = db1.insertdata(
                    ""+imageUri,
                    ""+titles,
                    ""+price,
                    ""+categorys,
                    ""+descreptions,
                    ""+tels,
                    ""+users


            );

            Toast.makeText(add_product.this,"added...",Toast.LENGTH_LONG).show();
            Intent intent2 = new Intent(add_product.this, HomeActivity.class);
            intent2.putExtra("user",users);
            startActivity(intent2);

        }

    }


    private void imagepickdialog() {
        String[] options = {"Camera", "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Image From");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    if(!checkcamerarmission()){
                        requestcamerapermission();
                    }else{
                        pickFromCamera();

                    }
                }else if(which==1){
                    if (checkstoragepermission()){
                        requeststoragepermission();
                    } else{
                        pickFromGallery();
                        
                    }
                }
            }
        });
        builder.create().show();

    }

    private void pickFromGallery() {
     // Intent galleryIntent = new Intent(Intent.ACTION_VIEW);
       // galleryIntent.setType("image/*");


     //   startActivityForResult(galleryIntent,IMAGE_PICK_GALLERY_CODE);
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
          startActivityForResult(galleryIntent,IMAGE_PICK_GALLERY_CODE);


        // pass the constant to compare it
        // with the returned requestCode
      //  startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), SELECT_PICTURE);
    }


    private void pickFromCamera() {
        ContentValues cv = new ContentValues();
        cv.put(MediaStore.Images.Media.TITLE,"IMAGE TITLE");
        cv.put(MediaStore.Images.Media.DESCRIPTION,"IMAGE DESCRIPTION");
        imageUri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,cv);
        Intent CameraIntent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        CameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(CameraIntent,IMAGE_PICK_CAMERA_CODE);



    }

    private boolean checkstoragepermission(){
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) ==(PackageManager.PERMISSION_GRANTED);
        return result;
    }
    private void requeststoragepermission(){
        ActivityCompat.requestPermissions(this,storagePermissions,STORAGE_REQUEST);
    }
    private boolean checkcamerarmission(){
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) ==(PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) ==(PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }
    private void requestcamerapermission(){
        ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == RESULT_OK){
            Toast.makeText(add_product.this,"RESULT_OK ",Toast.LENGTH_LONG).show();


            if (requestCode== SELECT_PICTURE){
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }else if (requestCode== IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else
                if (requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){

                    CropImage.ActivityResult result= CropImage.getActivityResult(data);
                    if(requestCode == RESULT_OK){
                        Uri resultUri =result.getUri();
                        imageUri = resultUri;
                        addphoto.setImageURI(resultUri);

                    }
                else
                if (resultCode== CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                    Toast.makeText(add_product.this, ""+error, Toast.LENGTH_LONG).show();

                }

                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CAMERA_REQUEST:{
                if(grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }else{
                        Toast.makeText(add_product.this,"Camera and storage permissions are required ",Toast.LENGTH_LONG).show();
                    }
                }


            }
            break;
            case STORAGE_REQUEST:{
                if(grantResults.length>0){
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        Toast.makeText(add_product.this,"storage permissions  ",Toast.LENGTH_LONG).show();
                        pickFromGallery();


                    }else{
                        Toast.makeText(add_product.this,"storage permissions are required ",Toast.LENGTH_LONG).show();
                    }
                }


            }
            break;
        }
    }


}