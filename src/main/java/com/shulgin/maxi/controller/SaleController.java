package com.shulgin.maxi.controller;

import com.shulgin.maxi.entity.Product;
import com.shulgin.maxi.entity.Sale;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class SaleController {
    private List<Product> products = new ArrayList<Product>() {{
        add(new Product(10, "Water", 1, new BigDecimal(3)));
        add(new Product(15, "Bread", 1, new BigDecimal(6)));
        add(new Product(17, "Ice", 2, new BigDecimal(5)));
    }};

    private List<Sale> sales = new ArrayList<Sale>() {{
        add(new Sale("12345555", 12334444, products));
    }};

    @GetMapping("/sales")
    public List<Sale> saleList() {
        return sales;
    }

    @GetMapping("/sales/{cardNumber}")
    public Sale sale(@PathVariable String cardNumber) {
        return sales.stream()
                .filter(x -> x.getCardNumber().equals(cardNumber))
                .findFirst()
                .get();
    }

}
