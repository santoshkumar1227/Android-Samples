package com.venkatesh.shoppig.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.venkatesh.shoppig.R;


public class ShopRecyclerViewHolder extends RecyclerView.ViewHolder {

    public final ImageView produceImage;
    public final TextView productName;
    public final TextView productPrice;

    public ShopRecyclerViewHolder(View itemView) {
        super(itemView);
        produceImage = itemView.findViewById(R.id.product_image);
        productName = itemView.findViewById(R.id.product_name);
        productPrice = itemView.findViewById(R.id.product_price);

    }
}
