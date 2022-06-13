package com.shulgin.maxi.util;

import com.shulgin.maxi.entity.Product;
import com.shulgin.maxi.entity.Sale;

import java.math.BigDecimal;
import java.util.List;

public class SaleUtils {

    public static BigDecimal calculateSumProducts(Sale sale) {
        List<Product> products = sale.getProducts();
        if(products == null) {
            return BigDecimal.ZERO;
        }
        return products.stream()
                .map(Product::getPriceProduct)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
