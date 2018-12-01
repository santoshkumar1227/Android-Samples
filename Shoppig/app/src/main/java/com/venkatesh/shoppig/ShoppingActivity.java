package com.venkatesh.shoppig;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;


import com.venkatesh.shoppig.adapters.ShopRecyclerViewAdapter;
import com.venkatesh.shoppig.entities.Filter;
import com.venkatesh.shoppig.entities.ProductObject;
import com.venkatesh.shoppig.helpers.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShoppingActivity extends AppCompatActivity implements FilterDialogFragment.Listener {
    private ArrayList<Filter> filters = new ArrayList<>();
    private ArrayList<ProductObject> products;
    private ShopRecyclerViewAdapter shopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        RecyclerView shoppingRecyclerView = findViewById(R.id.product_list);
        StaggeredGridLayoutManager mGrid = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        shoppingRecyclerView.setLayoutManager(mGrid);
        shoppingRecyclerView.setHasFixedSize(true);
        shoppingRecyclerView.addItemDecoration(new SpacesItemDecoration(2, 12, false));
        /*if (getIntent().hasExtra("type") && getIntent().getStringExtra("type").matches("men")) {
            shopAdapter = new ShopRecyclerViewAdapter(ShoppingActivity.this, getAllProductsOnSaleMen());
        } else {
            shopAdapter = new ShopRecyclerViewAdapter(ShoppingActivity.this, getAllProductsOnSaleWomen());
        }*/
        getAllProducts();
        shopAdapter = new ShopRecyclerViewAdapter(ShoppingActivity.this, products);
        shoppingRecyclerView.setAdapter(shopAdapter);
        filters();
    }

    private void filters() {
        filters.add(new Filter("Male", false));
        filters.add(new Filter("FeMale", false));
        filters.add(new Filter("Sort by Price Descending", false));
    }


    private void getAllProducts() {
        products = new ArrayList<>();
        products.add(new ProductObject(1, "Nautica Men Printed Casual Spread Shirt", R.drawable.one, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Black", "Male"));
        products.add(new ProductObject(2, "Tripr Men Solid Casual Shirt", R.drawable.two, "Beautiful sleek black top for casual outfit and evening walk", 30, 38, "Black", "Male"));
        products.add(new ProductObject(3, "VERO LIE Men's Solid Casual Shirt", R.drawable.three, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "White", "Male"));
        products.add(new ProductObject(4, "NautTripr Men Solid Casual Shirt", R.drawable.four, "Beautiful sleek black top for casual outfit and evening walk", 40, 38, "Dark Blue", "Male"));
        products.add(new ProductObject(5, "Feed Up Men's Solid Casual Shirt", R.drawable.five, "Beautiful sleek black top for casual outfit and evening walk", 70, 38, "Spotted Green", "Male"));
        products.add(new ProductObject(6, "Nautica Men Printed Casual Spread Shirt", R.drawable.one, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Black", "Male"));
        products.add(new ProductObject(7, "Tripr Men Solid Casual Shirt", R.drawable.two, "Beautiful sleek black top for casual outfit and evening walk", 50, 38, "Black", "Male"));
        products.add(new ProductObject(8, "VERO LIE Men's Solid Casual Shirt", R.drawable.three, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "White", "Male"));
        products.add(new ProductObject(9, "NautTripr Men Solid Casual Shirt", R.drawable.four, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Dark Blue", "Male"));
        products.add(new ProductObject(10, "Feed Up Men's Solid Casual Shirt", R.drawable.five, "Beautiful sleek black top for casual outfit and evening walk", 30, 38, "Spotted Green", "Male"));
        products.add(new ProductObject(11, "Nautica Men Printed Casual Spread Shirt", R.drawable.one, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Black", "Male"));
        products.add(new ProductObject(12, "Tripr Men Solid Casual Shirt", R.drawable.two, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Black", "Male"));
        products.add(new ProductObject(13, "VERO LIE Men's Solid Casual Shirt", R.drawable.three, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "White", "Male"));
        products.add(new ProductObject(14, "NautTripr Men Solid Casual Shirt", R.drawable.four, "Beautiful sleek black top for casual outfit and evening walk", 70, 38, "Dark Blue", "Male"));
        products.add(new ProductObject(15, "Feed Up Men's Solid Casual Shirt", R.drawable.five, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Spotted Green", "Male"));
        products.add(new ProductObject(16, "Nautica Men Printed Casual Spread Shirt", R.drawable.one, "Beautiful sleek black top for casual outfit and evening walk", 80, 38, "Black", "Male"));
        products.add(new ProductObject(17, "Tripr Men Solid Casual Shirt", R.drawable.two, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Black", "Male"));
        products.add(new ProductObject(18, "VERO LIE Men's Solid Casual Shirt", R.drawable.three, "Beautiful sleek black top for casual outfit and evening walk", 10, 38, "White", "Male"));
        products.add(new ProductObject(19, "NautTripr Men Solid Casual Shirt", R.drawable.four, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Dark Blue", "Male"));
        products.add(new ProductObject(20, "Feed Up Men's Solid Casual Shirt", R.drawable.five, "Beautiful sleek black top for casual outfit and evening walk", 40, 38, "Spotted Green", "Male"));
        products.add(new ProductObject(21, "Sleek Black Top", R.drawable.six, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "Black", "Female"));
        products.add(new ProductObject(22, "Flare Black Gown", R.drawable.seven, "Beautiful sleek black top for casual outfit and evening walk", 30, 38, "Black", "Female"));
        products.add(new ProductObject(23, "Flare White Blouse", R.drawable.eight, "Beautiful sleek black top for casual outfit and evening walk", 40, 38, "White", "Female"));
        products.add(new ProductObject(24, "Blue Swed Gown", R.drawable.nine, "Beautiful sleek black top for casual outfit and evening walk", 50, 38, "Dark Blue", "Female"));
        products.add(new ProductObject(25, "Spotted Gown", R.drawable.ten, "Beautiful sleek black top for casual outfit and evening walk", 40, 38, "Spotted Green", "Female"));
        products.add(new ProductObject(26, "Sleek Black Top", R.drawable.six, "Beautiful sleek black top for casual outfit and evening walk", 30, 38, "Black", "Female"));
        products.add(new ProductObject(27, "Flare Black Gown", R.drawable.seven, "Beautiful sleek black top for casual outfit and evening walk", 10, 38, "Black", "Female"));
        products.add(new ProductObject(28, "Flare White Blouse", R.drawable.eight, "Beautiful sleek black top for casual outfit and evening walk", 20, 38, "White", "Female"));
        products.add(new ProductObject(29, "Blue Swed Gown", R.drawable.nine, "Beautiful sleek black top for casual outfit and evening walk", 30, 38, "Dark Blue", "Female"));
        products.add(new ProductObject(30, "Spotted Gown", R.drawable.ten, "Beautiful sleek black top for casual outfit and evening walk", 40, 38, "Spotted Green", "Female"));
        products.add(new ProductObject(31, "Sleek Black Top", R.drawable.six, "Beautiful sleek black top for casual outfit and evening walk", 40, 38, "Black", "Female"));
        products.add(new ProductObject(32, "Flare Black Gown", R.drawable.seven, "Beautiful sleek black top for casual outfit and evening walk", 50, 38, "Black", "Female"));
        products.add(new ProductObject(33, "Flare White Blouse", R.drawable.eight, "Beautiful sleek black top for casual outfit and evening walk", 60, 38, "White", "Female"));
        products.add(new ProductObject(34, "Blue Swed Gown", R.drawable.nine, "Beautiful sleek black top for casual outfit and evening walk", 50, 38, "Dark Blue", "Female"));
        products.add(new ProductObject(35, "Spotted Gown", R.drawable.ten, "Beautiful sleek black top for casual outfit and evening walk", 30, 38, "Spotted Green", "Female"));
        Collections.shuffle(products);
    }

    private List<ProductObject> getAllProductsOnSaleWomen() {
        List<ProductObject> products = new ArrayList<>();
        return products;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                FilterDialogFragment bottomSheetDialogFragment = FilterDialogFragment.newInstance(filters);
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "FilterDialogFragment");
                break;
            case R.id.orders:
                startActivity(new Intent(getApplicationContext(), ShowOrdersActivity.class));
                break;
            case R.id.logout:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onItemClicked(ArrayList<Filter> filters) {
        ArrayList<ProductObject> filteredList = new ArrayList<>();
        this.filters = filters;
        for (Filter filter : filters) {
            if (filter.isChecked()) {
                switch (filter.getType()) {
                    case "Male":
                        for (ProductObject productObject : products) {
                            if (productObject.getProductGender().matches("Male")) {
                                filteredList.add(productObject);
                            }
                        }
                        break;

                    case "FeMale":
                        for (ProductObject productObject : products) {
                            if (productObject.getProductGender().matches("Female")) {
                                filteredList.add(productObject);
                            }
                        }
                        break;

                    case "Sort by Price Descending":
                        filteredList = filteredList.size() != 0 ? filteredList : products;
                        Collections.shuffle(filteredList);
                        Collections.sort(filteredList, new Comparator<ProductObject>() {
                            @Override
                            public int compare(ProductObject o1, ProductObject o2) {
                                return o2.getProductPrice() - o1.getProductPrice();
                            }
                        });
                        break;
                }
            }
        }
        shopAdapter.updateData(filteredList);
    }
}
