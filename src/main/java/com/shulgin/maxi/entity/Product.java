package com.shulgin.maxi.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
public class Product {

    @Id
    private Long productCode;
    private String productName;
    private BigDecimal priceProduct;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    Set<ProductSale> productSale;

    public Product() {}

    public Product(Long productCode, String productName, BigDecimal priceProduct) {
        this.productCode = productCode;
        this.productName = productName;
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

    public BigDecimal getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(BigDecimal priceProduct) {
        this.priceProduct = priceProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", productCode=" + productCode +
                ", productName='" + productName + '\'' +
                ", priceProduct=" + priceProduct +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productCode, product.productCode) && Objects.equals(productName, product.productName) && Objects.equals(priceProduct, product.priceProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, productName, priceProduct);
    }
}
