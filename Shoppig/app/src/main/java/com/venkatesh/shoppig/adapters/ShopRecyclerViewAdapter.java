package com.venkatesh.shoppig.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.venkatesh.shoppig.ProductActivity;
import com.venkatesh.shoppig.R;
import com.venkatesh.shoppig.entities.ProductObject;

import java.util.ArrayList;
import java.util.List;

public class ShopRecyclerViewAdapter extends RecyclerView.Adapter<ShopRecyclerViewHolder> {

    private final Context context;

    private List<ProductObject> allProducts;

    public ShopRecyclerViewAdapter(Context context, List<ProductObject> allProducts) {
        this.context = context;
        this.allProducts = allProducts;
    }

    @Override
    public ShopRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_listing, parent, false);
        return new ShopRecyclerViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ShopRecyclerViewHolder holder, int position) {
        final ProductObject singleProduct = allProducts.get(position);

        holder.productName.setText(singleProduct.getProductName());
        holder.produceImage.setImageResource(singleProduct.getProductImage());
        holder.productPrice.setText("$" + (int) singleProduct.getProductPrice());

        holder.produceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent productIntent = new Intent(context, ProductActivity.class);

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                String stringObjectRepresentation = gson.toJson(singleProduct);

                productIntent.putExtra("PRODUCT", stringObjectRepresentation);
                context.startActivity(productIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allProducts.size();
    }

    public void updateData(ArrayList<ProductObject> filteredList) {
        this.allProducts = filteredList;
        notifyDataSetChanged();
    }
}
