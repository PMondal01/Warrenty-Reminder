package com.bits.warrentyguaranteed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProductDateActivity extends AppCompatActivity {

    EditText editText_purchase_date, editText_expiray_date;
    DatePickerDialog datePickerDialog;
    Button button;
    MySharedPreferences myPreferences;
    MySqliteDatabaseHelper mySqliteDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_date);
        myPreferences= MySharedPreferences.getPreferences(this);
        mySqliteDatabaseHelper=new MySqliteDatabaseHelper(this);

        final DatePicker datePicker=new DatePicker(this);
        editText_purchase_date=findViewById(R.id.purchaseproductdate);
        editText_expiray_date=findViewById(R.id.expirydate);
        button=findViewById(R.id.next);
        final Calendar calendar=Calendar.getInstance();
        //purchaseDate
        String currentDate= DateFormat.getDateInstance().format(calendar.getTime());
        editText_purchase_date.setText(currentDate);
        editText_purchase_date.setOnClickListener(new View.OnClickListener() {
            int Date=datePicker.getDayOfMonth();
            int Month=(datePicker.getMonth());
            int Year=datePicker.getYear();
            @Override
            public void onClick(View v) {
                datePickerDialog=new DatePickerDialog(ProductDateActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String monthName = "";
                                if (month >= 0 && month < 12)
                                    try {
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.set(Calendar.MONTH, month);
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM");
                                        //simpleDateFormat.setCalendar(calendar);
                                        monthName = simpleDateFormat.format(calendar.getTime());
                                    } catch (Exception e) {
                                        if (e != null)
                                            e.printStackTrace();
                                    }
                                String date=monthName+" "+dayOfMonth+", "+year;
                                editText_purchase_date.setText(date);

                            }
                        },Year,Month,Date);
                datePickerDialog.show();

            }
        });
        //warrentyPeriod
        editText_expiray_date.setOnClickListener(new View.OnClickListener() {
            int Date=datePicker.getDayOfMonth();
            int Month=(datePicker.getMonth());
            int Year=datePicker.getYear();
            @Override
            public void onClick(View v) {
                datePickerDialog=new DatePickerDialog(ProductDateActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String monthName = "";
                                if (month >= 0 && month < 12)
                                    try {
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.set(Calendar.MONTH, month);
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM");
                                        //simpleDateFormat.setCalendar(calendar);
                                        monthName = simpleDateFormat.format(calendar.getTime());
                                    } catch (Exception e) {
                                        if (e != null)
                                            e.printStackTrace();
                                    }
                                String date=monthName+" "+dayOfMonth+", "+year;
                                editText_expiray_date.setText(date);

                            }
                        },Year,Month,Date);
                datePickerDialog.show();

            }
        });

        //data save
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String purchase_date=editText_purchase_date.getText().toString();
                String expiary_date=editText_expiray_date.getText().toString();
                myPreferences.setPurchaseDate(purchase_date);
                myPreferences.setExpirayDate(expiary_date);

                   //retrive all data
                 String productName= myPreferences.getProduct_Name();
                 String productImage=myPreferences.getProductImage();
                 String purchaseDate=myPreferences.getPurchaseDate();
                 String expirayDate= myPreferences.getExpirayDate();

                 //save all data in sqlite
                long rowId= mySqliteDatabaseHelper.insertData(productName,productImage,purchaseDate,expirayDate);
                if(rowId>0){
                    Toast.makeText(getApplicationContext(),"Row "+rowId+" inserted",Toast.LENGTH_LONG).show();
                    mySqliteDatabaseHelper.close();
                }else {
                    Toast.makeText(getApplicationContext(),"Row insert failed",Toast.LENGTH_LONG).show();
                }


            }
        });

    }

}
