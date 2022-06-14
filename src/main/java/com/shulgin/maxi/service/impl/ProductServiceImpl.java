package com.shulgin.maxi.service.impl;

import com.shulgin.maxi.entity.Product;
import com.shulgin.maxi.repository.ProductRepo;
import com.shulgin.maxi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public boolean addProduct(Product product) {
        if(productRepo.findProductByProductCode(product.getProductCode()) != null) {
            return false;
        }
        productRepo.saveAndFlush(product);
        return true;
    }
}
