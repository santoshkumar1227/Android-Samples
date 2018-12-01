package com.venkatesh.shoppig.entities;

public class Order {
    private String orderDate="",orderDesc="",orderQnty="",orderPrice="";
    private int orderId;
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderQnty() {
        return orderQnty;
    }

    public void setOrderQnty(String orderQnty) {
        this.orderQnty = orderQnty;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }
}
