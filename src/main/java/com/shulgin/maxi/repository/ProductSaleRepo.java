package com.shulgin.maxi.repository;

import com.shulgin.maxi.entity.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;

@Repository
public interface ProductSaleRepo extends JpaRepository<ProductSale, Long> {
    @Query(value = "select sum(p.product_price*ps.count) from sale s " +
            "inner join product_sale ps on ps.sale_id=s.id " +
            "inner join product p on ps.product_id=p.product_code " +
            "where s.date=:date", nativeQuery=true)
    BigDecimal sumAllSalesByDate(@Param("date") Date date);
}
