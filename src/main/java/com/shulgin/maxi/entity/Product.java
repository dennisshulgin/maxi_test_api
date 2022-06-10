package com.shulgin.maxi.entity;

import java.math.BigDecimal;

public class Product {

    private int productCode;
    private String productName;
    private int countProduct;
    private BigDecimal priceProduct;

    public Product() {}

    public Product(int productCode, String productName, int countProduct, BigDecimal priceProduct) {
        this.productCode = productCode;
        this.productName = productName;
        this.countProduct = countProduct;
        this.priceProduct = priceProduct;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }

    public BigDecimal getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(BigDecimal priceProduct) {
        this.priceProduct = priceProduct;
    }
}
