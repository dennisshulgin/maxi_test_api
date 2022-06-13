package com.shulgin.maxi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    private Long productCode;
    private String productName;
    private int countProduct;
    private BigDecimal priceProduct;

    public Product() {}

    public Product(Long productCode, String productName, int countProduct, BigDecimal priceProduct) {
        this.productCode = productCode;
        this.productName = productName;
        this.countProduct = countProduct;
        this.priceProduct = priceProduct;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
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

    @Override
    public String toString() {
        return "Product{" +
                "productCode=" + productCode +
                ", productName='" + productName + '\'' +
                ", countProduct=" + countProduct +
                ", priceProduct=" + priceProduct +
                '}';
    }
}
