package com.venkatesh.shoppig.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.venkatesh.shoppig.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Orders";
    private static final String ORDER_ID = "OrderId";
    private static final String ORDER_DESC = "OrderDesc";
    private static final String ORDER_QNTY = "OrderQnty";
    private static final String ORDER_PRICE = "OrderPrice";
    private static final String ORDER_TIMESTAMP = "OrderTimestamp";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ORDER_QNTY + " TEXT,"
                    + ORDER_PRICE + " TEXT,"
                    + ORDER_DESC + " TEXT,"
                    + ORDER_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "orders_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void insertOrders(List<Order> orders) {
        for (Order order : orders) {
            insertOrder(order);
        }
    }

    public long insertOrder(Order order) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ORDER_DESC, order.getOrderDesc());
        values.put(ORDER_QNTY, order.getOrderQnty());
        values.put(ORDER_PRICE, order.getOrderPrice());

        // insert row
        long id = db.insert(TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public List<Order> getAllOrders() {
        List<Order> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " +
                ORDER_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Order note = new Order();
                note.setOrderId(cursor.getInt(cursor.getColumnIndex(ORDER_ID)));
                note.setOrderDesc(cursor.getString(cursor.getColumnIndex(ORDER_DESC)));
                note.setOrderPrice(cursor.getString(cursor.getColumnIndex(ORDER_PRICE)));
                note.setOrderDate(cursor.getString(cursor.getColumnIndex(ORDER_TIMESTAMP)));
                note.setOrderQnty(cursor.getString(cursor.getColumnIndex(ORDER_QNTY)));
                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }
}