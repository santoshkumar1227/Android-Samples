package com.venkatesh.shoppig.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.venkatesh.shoppig.AdapterToActivity;
import com.venkatesh.shoppig.ProductActivity;
import com.venkatesh.shoppig.R;
import com.venkatesh.shoppig.entities.ProductObject;

import java.util.List;

public class CheckRecyclerViewAdapter extends RecyclerView.Adapter<CheckRecyclerViewHolder> {

    private List<ProductObject> mProductObject;
    private AdapterToActivity adapterToActivity;
    private Context context;

    public CheckRecyclerViewAdapter(Context context, List<ProductObject> mProductObject) {
        this.mProductObject = mProductObject;
        adapterToActivity = (AdapterToActivity) context;
        this.context=context;
    }

    @Override
    public CheckRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_layout, parent, false);
        return new CheckRecyclerViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(CheckRecyclerViewHolder holder, final int position) {
        //get product quantity
        holder.quantity.setText("Quantity :" + mProductObject.get(position).getQnty() + "");
        holder.productName.setText(mProductObject.get(position).getProductName());
        holder.productPrice.setText(String.valueOf(mProductObject.get(position).getProductPrice()) + " $");

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterToActivity.deleteItem(position);
            }
        });

        holder.productName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productIntent = new Intent(context, ProductActivity.class);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                String stringObjectRepresentation = gson.toJson(mProductObject.get(position));
                productIntent.putExtra("PRODUCT", stringObjectRepresentation);
                context.startActivity(productIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductObject.size();
    }

    public void updateData(List<ProductObject> productList) {
        this.mProductObject = productList;
        notifyDataSetChanged();
    }
}
