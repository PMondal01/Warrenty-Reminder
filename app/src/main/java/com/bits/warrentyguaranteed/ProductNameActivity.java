package com.bits.warrentyguaranteed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProductNameActivity extends AppCompatActivity {

    Button next;
    EditText product_name;
    MySharedPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_name);
        myPreferences= MySharedPreferences.getPreferences(this);

        next=findViewById(R.id.next);
        product_name=findViewById(R.id.edittext_productname);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductNameActivity.this,ProductImageActivity.class);
                String Product_Name=product_name.getText().toString();
                myPreferences.setProduct_Name(Product_Name);
                startActivity(intent);
            }
        });
    }
}
