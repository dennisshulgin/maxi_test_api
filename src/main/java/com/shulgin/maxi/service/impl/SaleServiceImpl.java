package com.shulgin.maxi.service.impl;

import com.shulgin.maxi.entity.Sale;
import com.shulgin.maxi.repository.SaleRepo;
import com.shulgin.maxi.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepo saleRepo;

    @Override
    public boolean addSale(Sale sale) {
        saleRepo.saveAndFlush(sale);
        return true;
    }

    @Override
    public List<Sale> findSaleByCardName(String cardName) {
        return saleRepo.findSaleByCardNumber(cardName);
    }

    @Override
    public List<Sale> findSaleByDate(long date) {
        return saleRepo.findSaleByDate(date);
    }
}
