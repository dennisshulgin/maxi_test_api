package com.shulgin.maxi.service;

import com.shulgin.maxi.entity.Sale;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SaleService {
    boolean addSale(Sale sale);
    List<Sale> findSaleByCardName(String cardName);
    List<Sale> findSaleByDate(long date);
}
