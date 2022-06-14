package com.shulgin.maxi.repository;

import com.shulgin.maxi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findProductByProductCode(Long productCode);

    @Query(value = "select p.product_code, p.product_name, p.product_price, sum(ps.count) count from sale s " +
            "inner join product_sale ps on ps.sale_id=s.id " +
            "inner join product p on ps.product_id=p.product_code " +
            "where s.card_number=:cardname " +
            "group by p.product_name, p.product_price, p.product_code " +
            "order by count desc limit :count", nativeQuery = true)
    List<Product> findTopProductsByCardName(@Param("cardname")String cardName,
                                            @Param("count") int count);
}
