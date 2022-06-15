package com.shulgin.maxi.controller;

import com.shulgin.maxi.entity.Product;
import com.shulgin.maxi.entity.Sum;
import com.shulgin.maxi.service.ProductSaleService;
import com.shulgin.maxi.service.ProductService;
import com.shulgin.maxi.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class SaleController {

    @Autowired
    private ProductService productService;
    @Autowired
    private SaleService saleService;
    @Autowired
    private ProductSaleService productSaleService;

    @GetMapping("/sales.sum")
    public Sum saleList(@RequestParam("date") Long date) {
        Date sqlDate = new Date(date);
        BigDecimal sum = productSaleService.sumAllSalesByDate(sqlDate);
        sum = sum == null ? BigDecimal.ZERO : sum;
        return new Sum(sum);
    }

    @GetMapping("/sales.top")
    public List<Product> sale(@RequestParam("card") String cardNumber, @RequestParam int limit) {
        List<Product> products = productService.findTopProductsByCardName(cardNumber, limit);
        products = products == null ? new ArrayList<>() : products;
        return products;
    }

}
