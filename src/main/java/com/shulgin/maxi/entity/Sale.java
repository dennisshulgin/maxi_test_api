package com.shulgin.maxi.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cardNumber;

    private java.sql.Date date;

    private java.sql.Time time;

    @OneToMany(mappedBy = "sale", fetch = FetchType.EAGER)
    Set<ProductSale> productSale;

    public Sale() {}

    public Sale(String cardNumber, java.sql.Date date, java.sql.Time time) {
        this.cardNumber = cardNumber;
        this.date = date;
        this.time = time;
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

    public Set<ProductSale> getProductSale() {
        return productSale;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "cardNumber='" + cardNumber + '\'' +
                ", date=" + date +
                '}';
    }
}
