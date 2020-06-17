package com.bits.warrentyguaranteed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDatabaseHelper myDatabaseHelper;
    private ListView product_Listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper=new MyDatabaseHelper(this);
        product_Listview=findViewById(R.id.productlist_view);
        UpdateUI();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_product:
                final EditText productEditText=new EditText(this);
                final EditText shopnameEditText=new EditText(this);

                AlertDialog dialog=new AlertDialog.Builder(this)
                        .setTitle("New Product")
                        .setMessage("Add a new product")
                        .setView(productEditText)
                       /* .setMessage("Shop name")*/
                     /*   .setView(shopnameEditText)*/
                        .setPositiveButton("Add",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String product_name=String.valueOf(productEditText.getText());
                                /*String shop_name=String.valueOf(shopnameEditText.getText());*/
                                long rowId= myDatabaseHelper.insertData(product_name);
                                if(rowId>0){
                                    Toast.makeText(getApplicationContext(),"Row "+rowId+" inserted",Toast.LENGTH_LONG).show();
                                    myDatabaseHelper.close();
                                    UpdateUI();
                                }else {
                                    Toast.makeText(getApplicationContext(),"Row insert failed",Toast.LENGTH_LONG).show();
                                }
                            }
                        } )
                        .setNegativeButton("Cancel",null)
                        .create();
                        dialog.show();
                        return true;
            default:
                        return super.onOptionsItemSelected(item);
        }
    }

    private void UpdateUI(){

        ArrayList<String> productList=new ArrayList<>();
        Cursor cursor=myDatabaseHelper.displayAllData();
        if(cursor.getCount()==0){
            return;
        }else {
            while (cursor.moveToNext()){
                productList.add(cursor.getString(0)+"\t"+cursor.getString(1));
            }
        }
         ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,R.layout.product_list,R.id.demo_product,productList);
         product_Listview.setAdapter(arrayAdapter);

    }



    private void DeleteTask(View view){

    }
}
