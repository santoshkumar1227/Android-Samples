package com.venkatesh.shoppig;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.venkatesh.shoppig.adapters.CheckRecyclerViewAdapter;
import com.venkatesh.shoppig.db.DatabaseHelper;
import com.venkatesh.shoppig.entities.Order;
import com.venkatesh.shoppig.entities.ProductObject;
import com.venkatesh.shoppig.helpers.MySharedPreference;
import com.venkatesh.shoppig.helpers.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity implements AdapterToActivity {

    private List<ProductObject> productList;
    private CheckRecyclerViewAdapter mAdapter;
    private MySharedPreference sharedPreference;
    private Gson gson;
    private TextView subTotal;
    MySharedPreference mShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("Over Cart");
        sharedPreference = new MySharedPreference(CheckoutActivity.this);
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
        subTotal = findViewById(R.id.sub_total);

        RecyclerView checkRecyclerView = findViewById(R.id.checkout_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CheckoutActivity.this);
        checkRecyclerView.setLayoutManager(linearLayoutManager);
        checkRecyclerView.setHasFixedSize(true);
        checkRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(CheckoutActivity.this));

        // get content of cart
        mShared = new MySharedPreference(CheckoutActivity.this);

        ProductObject[] addCartProducts = gson.fromJson(mShared.retrieveProductFromCart(), ProductObject[].class);
        productList = convertObjectArrayToListObject(addCartProducts);

        mAdapter = new CheckRecyclerViewAdapter(CheckoutActivity.this, productList);
        checkRecyclerView.setAdapter(mAdapter);
        checkItems();


        Button shoppingButton = findViewById(R.id.shopping);
        assert shoppingButton != null;
        shoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shoppingIntent = new Intent(CheckoutActivity.this, ShoppingActivity.class);
                startActivity(shoppingIntent);
            }
        });
    }

    private List<ProductObject> convertObjectArrayToListObject(ProductObject[] allProducts) {
        List<ProductObject> mProduct = new ArrayList<>();
        if (allProducts != null) {
            for (int i = 0; i < allProducts.length; i++) {
                ProductObject mProductObject = allProducts[i];
                for (int j = 0; j < mProduct.size(); j++) {
                    ProductObject productObject = mProduct.get(j);
                    if (productObject.getProductId() == mProductObject.getProductId()) {
                        mProductObject.setQnty(mProductObject.getQnty() + +mProduct.get(j).getQnty());
                        mProduct.remove(j);
                    }
                }
                mProduct.add(mProductObject);
            }
        }
        return mProduct;
    }

    private double getTotalPrice(List<ProductObject> mProducts) {
        double totalCost = 0;
        for (int i = 0; i < mProducts.size(); i++) {
            ProductObject pObject = mProducts.get(i);
            int itemsCount = pObject.getQnty();
            totalCost = totalCost + (pObject.getProductPrice() * itemsCount);
        }
        return totalCost;
    }


    private double getTotalQnty(List<ProductObject> mProducts) {
        double totalQnty = 0;
        for (int i = 0; i < mProducts.size(); i++) {
            ProductObject pObject = mProducts.get(i);
            int itemsCount = pObject.getQnty();
            totalQnty = totalQnty + itemsCount;
        }
        return totalQnty;
    }

    private String getTotalOderDesc(List<ProductObject> mProducts) {
        String orderDesc = "";
        for (int i = 0; i < mProducts.size(); i++) {
            ProductObject pObject = mProducts.get(i);
            if (orderDesc.matches(""))
                orderDesc = pObject.getProductName();
            else
                orderDesc = orderDesc + " ," + pObject.getProductName();
        }
        return orderDesc;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void deleteItem(int pos) {
        productList.remove(pos);
        mAdapter.updateData(productList);
        String productsFromCart = sharedPreference.retrieveProductFromCart();
        String productsInCart = sharedPreference.retrieveProductFromCart();
        ProductObject[] storedProducts = gson.fromJson(productsInCart, ProductObject[].class);
        List<ProductObject> allNewProduct = convertObjectArrayToListObject(storedProducts);
        allNewProduct.remove(pos);
        String addAndStoreNewProduct = gson.toJson(allNewProduct);
        sharedPreference.addProductToTheCart(addAndStoreNewProduct);
        sharedPreference.addProductCount(getProductsCount(allNewProduct));
        checkItems();
    }

    private int getProductsCount(List<ProductObject> allNewProduct) {
        int count = 0;
        for (ProductObject productObject : allNewProduct) {
            count = count + productObject.getQnty();
        }
        return count;
    }

    private void checkItems() {
        if (productList.size() == 0) {
            subTotal.setText("No Items in Cart");
        } else {
            double mSubTotal = getTotalPrice(productList);
            subTotal.setText("Subtotal excluding tax and shipping: " + String.valueOf(mSubTotal) + " $");
        }
    }

    public void placeOrder(View view) {
        if (productList.size() == 0) {
            Toast.makeText(getApplicationContext(), "No Items added to cart", Toast.LENGTH_SHORT).show();
        } else {
            Order order = new Order();
            order.setOrderQnty(String.valueOf(getTotalQnty(productList)));
            order.setOrderPrice(String.valueOf(getTotalPrice(productList)));
            order.setOrderDesc(getTotalOderDesc(productList));

            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            long id = db.insertOrder(order);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //Setting message manually and performing action on button click
            builder.setMessage("Order places successfully")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mShared.addProductToTheCart("");
                            mShared.addProductCount(0);
                            Intent next = new Intent(getApplicationContext(), ShoppingActivity.class);
                            next.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            next.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(next);
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}

