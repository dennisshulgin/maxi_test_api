package com.shulgin.maxi.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

@Service
public interface ProductSaleService {
    boolean addProductSale(Long saleId, Map<Long, Integer> products);
    BigDecimal sumAllSalesByDate(Date date);
}
