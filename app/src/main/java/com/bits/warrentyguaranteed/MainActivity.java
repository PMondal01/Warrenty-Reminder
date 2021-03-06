package com.bits.warrentyguaranteed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MySqliteDatabaseHelper myDatabaseHelper;
    private ListView product_Listview;
    private String selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab=findViewById(R.id.fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductNameActivity.class);
                startActivity(intent);
            }
        });

        myDatabaseHelper=new MySqliteDatabaseHelper(this);
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

                final AlertDialog.Builder builder=new AlertDialog.Builder(this);
                View view=getLayoutInflater().inflate(R.layout.alertbox_addproduct,null);
                final EditText Product=view.findViewById(R.id.product_name_alert);
                final EditText Shop=view.findViewById(R.id.shop_name_alert);
                final EditText Warrenty_Period=view.findViewById(R.id.warrenty_period_alert);
                final CalendarView calendarView=view.findViewById(R.id.calender);
                Button Save=view.findViewById(R.id.save_btn_alert);

                try {
                    calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                        @Override
                        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                            selectedDate=Integer.toString(year)+Integer.toString(month)+Integer.toString(dayOfMonth);
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Exception: "+e,Toast.LENGTH_LONG).show();

                }

                builder.setView(view);
                final  AlertDialog dialog=builder.create();
                dialog.show();


                Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String product_name=String.valueOf(Product.getText());
                        String shop_name=String.valueOf(Shop.getText());
                        String warrenty=String.valueOf(Warrenty_Period.getText());
                        long rowId= myDatabaseHelper.insertData(product_name,shop_name,warrenty,selectedDate);
                        if(rowId>0){
                            Toast.makeText(getApplicationContext(),"Row "+rowId+" inserted",Toast.LENGTH_LONG).show();
                            myDatabaseHelper.close();
                            dialog.cancel();
                            UpdateUI();
                        }else {
                            Toast.makeText(getApplicationContext(),"Row insert failed",Toast.LENGTH_LONG).show();
                        }

                    }
                });
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
                productList.add(cursor.getString(0)+"\t"+cursor.getString(1)+"\t"+cursor.getString(2)+"\t"+cursor.getString(3)+"\t"+cursor.getString(4));
            }
        }
         ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,R.layout.product_list,R.id.product_name_list,productList);
         product_Listview.setAdapter(arrayAdapter);

        /* ArrayAdapter<String> shoparrayAdapter=new ArrayAdapter<>(this,R.layout.product_list,R.id.shop_name_list,productList);
         product_Listview.setAdapter(shoparrayAdapter);*/

    }

}
