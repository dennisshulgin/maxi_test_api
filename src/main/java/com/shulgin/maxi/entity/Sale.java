package com.shulgin.maxi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cardNumber;
    private long date;

    @OneToMany(mappedBy = "sale", fetch = FetchType.EAGER)
    Set<ProductSale> productSale;

    public Sale() {}

    public Sale(String cardNumber, long date) {
        this.cardNumber = cardNumber;
        this.date = date;
    }

    public Long getId() {
        return id;
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

    public Set<ProductSale> getProductSale() {
        return productSale;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "cardNumber='" + cardNumber + '\'' +
                ", date=" + date +
                '}';
    }
}
