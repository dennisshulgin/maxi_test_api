package com.shulgin.maxi.repository;

import com.shulgin.maxi.entity.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSaleRepo extends JpaRepository<ProductSale, Long> {
}
