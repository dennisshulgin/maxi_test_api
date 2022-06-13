package com.shulgin.maxi.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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

    @Override
    public String toString() {
        return "Sale{" +
                "cardNumber='" + cardNumber + '\'' +
                ", date=" + date +
                ", products=" + products +
                '}';
    }
}
