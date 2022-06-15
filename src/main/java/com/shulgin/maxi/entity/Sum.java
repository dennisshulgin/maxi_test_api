package com.shulgin.maxi.entity;

import java.math.BigDecimal;

public class Sum {
    private BigDecimal sum;

    public Sum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
