package com.shulgin.maxi.repository;

import com.shulgin.maxi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findProductByProductCode(Long productCode);
}
