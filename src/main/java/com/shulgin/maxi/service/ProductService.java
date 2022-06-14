package com.shulgin.maxi.service;

import com.shulgin.maxi.entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    boolean addProduct(Product product);
}
