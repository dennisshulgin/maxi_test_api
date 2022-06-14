package com.shulgin.maxi.repository;

import com.shulgin.maxi.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepo extends JpaRepository<Sale, Long> {
    Sale findSaleById(Long id);
    List<Sale> findSaleByCardNumber(String cardName);
    List<Sale> findSaleByDate(long date);
}
