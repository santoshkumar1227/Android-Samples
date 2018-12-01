package com.venkatesh.shoppig;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.venkatesh.shoppig.db.DatabaseHelper;
import com.venkatesh.shoppig.entities.Order;

import java.util.List;

public class ShowOrdersActivity extends AppCompatActivity {
    RecyclerView recyclerViewOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_orders);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewOrders = findViewById(R.id.ordersList);
        getOrdersFromDB();
    }

    private void getOrdersFromDB() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<Order> orders = db.getAllOrders();
        recyclerViewOrders.setAdapter(new OrderAdapter(orders));
    }

    class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
        List<Order> orders;

        OrderAdapter(List<Order> orders) {
            this.orders = orders;
        }

        @NonNull
        @Override
        public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new OrderViewHolder(LayoutInflater.from(ShowOrdersActivity.this).inflate(R.layout.item_order, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {
            Order order = orders.get(i);
            orderViewHolder.tvDate.setText(Html.fromHtml("Ordered Date : <strong>" + order.getOrderDate() + "</strong>"));
            orderViewHolder.tvDesc.setText(order.getOrderDesc());
            orderViewHolder.tvQnty.setText(Html.fromHtml("No of items : <strong>" + order.getOrderQnty() + "</strong>"));
            orderViewHolder.tvPrice.setText(Html.fromHtml("Total Price : <strong>" + order.getOrderPrice() + " $</strong>"));
        }

        @Override
        public int getItemCount() {
            return orders.size();
        }
    }

    private class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvDesc, tvQnty, tvDate,tvPrice;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvQnty = itemView.findViewById(R.id.tvQnty);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
