package com.shulgin.maxi.repository;

import com.shulgin.maxi.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Repository
public interface SaleRepo extends JpaRepository<Sale, Long> {
    Sale findSaleById(Long id);
    List<Sale> findSaleByCardNumber(String cardName);
    List<Sale> findSaleByDate(Date date);

}
