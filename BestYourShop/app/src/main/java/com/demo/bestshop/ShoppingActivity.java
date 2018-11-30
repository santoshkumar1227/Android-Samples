package com.demo.bestshop;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.bestshop.adapters.ShopRecyclerViewAdapter;
import com.demo.bestshop.entities.ProductObject;
import com.demo.bestshop.helpers.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ShoppingActivity extends AppCompatActivity {

    private static final String TAG = ShoppingActivity.class.getSimpleName();

    private RecyclerView shoppingRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        shoppingRecyclerView = (RecyclerView) findViewById(R.id.product_list);
        GridLayoutManager mGrid = new GridLayoutManager(ShoppingActivity.this, 2);
        shoppingRecyclerView.setLayoutManager(mGrid);
        shoppingRecyclerView.setHasFixedSize(true);
        shoppingRecyclerView.addItemDecoration(new SpacesItemDecoration(2, 12, false));
        ShopRecyclerViewAdapter shopAdapter;
        if (getIntent().hasExtra("type") && getIntent().getStringExtra("type").matches("men")) {
            shopAdapter = new ShopRecyclerViewAdapter(ShoppingActivity.this, getAllProductsOnSaleMen());
        } else {
            shopAdapter = new ShopRecyclerViewAdapter(ShoppingActivity.this, getAllProductsOnSaleWomen());
        }
        shoppingRecyclerView.setAdapter(shopAdapter);
    }


    private List<ProductObject> getAllProductsOnSaleMen() {
        List<ProductObject> products = new ArrayList<ProductObject>();
        products.add(new ProductObject(1, "Nautica Men Printed Casual Spread Shirt", R.drawable.one, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Black"));
        products.add(new ProductObject(1, "Tripr Men Solid Casual Shirt", R.drawable.two, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Black"));
        products.add(new ProductObject(1, "VERO LIE Men's Solid Casual Shirt", R.drawable.three, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "White"));
        products.add(new ProductObject(1, "NautTripr Men Solid Casual Shirt", R.drawable.four, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Dark Blue"));
        products.add(new ProductObject(1, "Feed Up Men's Solid Casual Shirt", R.drawable.five, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Spotted Green"));
        return products;
    }

    private List<ProductObject> getAllProductsOnSaleWomen() {
        List<ProductObject> products = new ArrayList<ProductObject>();
        products.add(new ProductObject(1, "Sleek Black Top", R.drawable.six, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Black"));
        products.add(new ProductObject(1, "Flare Black Gown", R.drawable.seven, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Black"));
        products.add(new ProductObject(1, "Flare White Blouse", R.drawable.eight, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "White"));
        products.add(new ProductObject(1, "Blue Swed Gown", R.drawable.nine, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Dark Blue"));
        products.add(new ProductObject(1, "Spotted Gown", R.drawable.ten, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Spotted Green"));
        return products;
    }
}
