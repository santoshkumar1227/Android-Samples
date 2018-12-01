package com.venkatesh.shoppig.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.venkatesh.shoppig.R;


public class CheckRecyclerViewHolder extends RecyclerView.ViewHolder{

    public final TextView quantity;
    public final TextView productName;
    public final TextView productPrice;
    public final ImageView delete;

    public CheckRecyclerViewHolder(View itemView) {
        super(itemView);

        quantity = itemView.findViewById(R.id.quantity);
        productName = itemView.findViewById(R.id.product_name);
        productPrice = itemView.findViewById(R.id.product_price);
        delete = itemView.findViewById(R.id.delete);
    }
}
