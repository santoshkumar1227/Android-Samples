package com.venkatesh.shoppig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class SelectionTypeActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);
        findViewById(R.id.tvMen).setOnClickListener(this);
        findViewById(R.id.tvWomen).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent nextIntent = new Intent(this, ShoppingActivity.class);
        if (view.getId() == R.id.tvMen) {
            nextIntent.putExtra("type", "men");
        } else if (view.getId() == R.id.tvWomen) {
            nextIntent.putExtra("type", "women");
        }
        startActivity(nextIntent);
    }
}
