package com.shulgin.maxi.service.impl;

import com.shulgin.maxi.exception.CheckParserException;
import com.shulgin.maxi.parser.SaxCheckParser;
import com.shulgin.maxi.service.ParserService;
import com.shulgin.maxi.service.ProductSaleService;
import com.shulgin.maxi.service.ProductService;
import com.shulgin.maxi.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParserServiceImpl implements ParserService {
    @Value("${check.directory}")
    private String path;

    @Autowired
    private ProductService productService;
    @Autowired
    private SaleService saleService;
    @Autowired
    private ProductSaleService productSaleService;

    @Scheduled(fixedDelay = 1 * 60 * 1000)
    public void parse() {
        System.out.println("start");
        SaxCheckParser saxCheckParser = new SaxCheckParser(productService, saleService, productSaleService);
        List<String> fileNames;
        try {
            fileNames = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(x -> x.endsWith(".xml"))
                    .collect(Collectors.toList());
            for (String name : fileNames) {
                saxCheckParser.parse(name);
            }
        } catch (IOException | CheckParserException e) {
            e.printStackTrace();
        }
    }
}
