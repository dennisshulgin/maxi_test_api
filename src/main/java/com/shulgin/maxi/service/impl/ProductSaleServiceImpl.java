package com.shulgin.maxi.service.impl;

import com.shulgin.maxi.entity.Product;
import com.shulgin.maxi.entity.ProductSale;
import com.shulgin.maxi.entity.Sale;
import com.shulgin.maxi.repository.ProductRepo;
import com.shulgin.maxi.repository.ProductSaleRepo;
import com.shulgin.maxi.repository.SaleRepo;
import com.shulgin.maxi.service.ProductSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductSaleServiceImpl implements ProductSaleService {

    @Autowired
    private ProductSaleRepo productSaleRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SaleRepo saleRepo;

    @Override
    public boolean addProductSale(Long saleId, Map<Long, Integer> products) {
        for(Map.Entry<Long, Integer> productCode : products.entrySet()) {
            Sale sale = saleRepo.findSaleById(saleId);
            Product product = productRepo.findProductByProductCode(productCode.getKey());
            ProductSale productSale = new ProductSale(product, sale, productCode.getValue());
            productSaleRepo.save(productSale);
        }
        return false;
    }

    @Override
    public BigDecimal sumAllSalesByDate(Date date) {
        return productSaleRepo.sumAllSalesByDate(date);
    }
}
