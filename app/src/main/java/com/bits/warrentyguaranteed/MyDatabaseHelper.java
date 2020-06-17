package com.bits.warrentyguaranteed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Product.db";
    private static final String TABLE_NAME="product_details";
    private static final String ID="_id";
    private static final String PRODUCT_NAME="product_name";
    private static final String SHOP_NAME="shop_name";
    private static final String PURCHASE_DATE="purchase_date";
    private static final String WARRENTY_PERIOD="warrenty_period";
    private static final int VERSION_NUMBER=1;
    private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+PRODUCT_NAME+" VARCHAR(255),"+SHOP_NAME+" VARCHAR(255))";
    private static final String SELECT_ALL="SELECT * FROM " +TABLE_NAME;
    private Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            Toast.makeText(context,"onCreate(): ",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }


    public long  insertData(String name){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(PRODUCT_NAME,name);
       /* contentValues.put(SHOP_NAME,shop_name);*/
        long rowId= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return  rowId;
    }

    public Cursor displayAllData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(SELECT_ALL,null);
        return cursor;
    }






}
