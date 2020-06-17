package com.bits.warrentyguaranteed;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="product_warrenty";
    private static final int DB_VER=1;
    public static final String DB_TABLE="product";
    public static final String DB_COLUMN="productList";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=String.format("CREATE TABLE &s (ID INTEGER PRIMARY KEY AUTOINCREMENT, &s TEXT NOT NULL",DB_TABLE,DB_COLUMN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
