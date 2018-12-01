package com.venkatesh.shoppig.entities;


public class ProductObject {

    private final int productId;

    private final String productName;

    private final int productImage;

    private final String productDescription;

    private final int productPrice;

    private final int productSize;

    private final String productColor;

    private final String productGender;

    private int qnty = 1;

    public int getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    public ProductObject(int productId, String productName, int productImage, String productDescription, int productPrice, int productSize, String productColor, String productGender) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.productColor = productColor;
        this.productGender = productGender;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductImage() {
        return productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getProductSize() {
        return productSize;
    }

    public String getProductColor() {
        return productColor;
    }

    public String getProductGender() {
        return productGender;
    }

    @Override
    public String toString() {
        return "Product id and name: " + productId + " " + productName;
    }
}
