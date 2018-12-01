package com.venkatesh.shoppig.helpers;


import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

    private final SharedPreferences prefs;

    public MySharedPreference(Context context){
        prefs = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
    }

    public void addProductToTheCart(String product){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(Constants.PRODUCT_ID, product);
        edits.apply();
    }


    public String retrieveProductFromCart(){
        return prefs.getString(Constants.PRODUCT_ID, "");
    }

    public void addProductCount(int productCount){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putInt(Constants.PRODUCT_COUNT, productCount);
        edits.apply();
    }

    public int retrieveProductCount(){
       return prefs.getInt(Constants.PRODUCT_COUNT, 0);
    }
}
