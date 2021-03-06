package com.bits.warrentyguaranteed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProductImageActivity extends AppCompatActivity {

    ImageView imageView,imageView_camera ,imageView_cameraImport;
    Button camera,camera_import;
    private static final int REQUEST_IMAGE_CAPTURE=101;
    private Uri imageuri;
    public int cameraOrimport=1;
    Button next;
    MySharedPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_image);
        myPreferences= MySharedPreferences.getPreferences(this);
        imageView=findViewById(R.id.image_view);
        camera=findViewById(R.id.camera);
        camera_import=findViewById(R.id.camera_import);
        imageView_cameraImport=findViewById(R.id.import_image);
        imageView_camera=findViewById(R.id.camera_image);
        next=findViewById(R.id.next);

        imageView_cameraImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraOrimport=1;
                openFilechooser();
            }
        });
        camera_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraOrimport=1;
                openFilechooser();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductImageActivity.this,ProductDateActivity.class);
                startActivity(intent);
            }
        });


    }

    private void openFilechooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
    }


    public void takePicture(View view)
    {
        cameraOrimport=2;
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (cameraOrimport==1){

            if (requestCode == REQUEST_IMAGE_CAPTURE && requestCode != RESULT_OK && data != null && data.getData() != null) {
                imageuri = data.getData(); //save selected image to imageuri
                Picasso.with(this).load(imageuri).into(imageView);
                Bitmap bitmap = null;
                try {
                     bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageuri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();
                String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
                myPreferences.setProductImage(imageEncoded);
            }

        }else {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {

                Bundle extras = data.getExtras();
                assert extras != null;
                Bitmap bitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(bitmap);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);

    }




}
