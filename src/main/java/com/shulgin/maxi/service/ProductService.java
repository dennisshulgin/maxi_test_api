package com.shulgin.maxi.service;

import com.shulgin.maxi.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    boolean addProduct(Product product);
    List<Product> findTopProductsByCardName(String cardName, int count);
}
