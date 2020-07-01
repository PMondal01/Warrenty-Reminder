package com.bits.warrentyguaranteed;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {

    private static MySharedPreferences myPreferences;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    MySharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("user_Response", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

   /* public MyPreferences(String product_name, String productimage, Text purchase_date, Text warrenty_date) {
    }*/

    public static MySharedPreferences getPreferences(Context context) {
        if (myPreferences == null) myPreferences = new MySharedPreferences(context);
        return myPreferences;
    }

    public String getProduct_Name(){
        return sharedPreferences.getString("product_name", null); //if user's age not found then it'll return -1
    }
    public void setProduct_Name(String product_name){
        editor.putString("product_name",product_name);
        editor.apply();
    }

    public String getProductImage(){
        return sharedPreferences.getString("productimage", null); //if user's age not found then it'll return -1
    }
    public void setProductImage(String productimage){
        editor.putString("productimage",productimage);
        editor.apply();
    }

    public String getPurchaseDate(){
        return sharedPreferences.getString("purchase_date", null); //if user's age not found then it'll return -1
    }
    public void setPurchaseDate(String purchase_date){
        editor.putString("purchase_date",purchase_date);
        editor.apply();
    }

    public String getExpirayDate(){
        return sharedPreferences.getString("warrenty_date", null); //if user's age not found then it'll return -1
    }
    public void setExpirayDate(String warrenty_date){
        editor.putString("warrenty_date",warrenty_date);
        editor.apply();
    }












}




