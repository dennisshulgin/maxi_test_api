package com.shulgin.maxi.entity;

import java.util.List;

public class Sale {
    private String cardNumber;
    private long date;
    List<Product> products;

    public Sale() {}

    public Sale(String cardNumber, long date, List<Product> products) {
        this.cardNumber = cardNumber;
        this.date = date;
        this.products = products;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
