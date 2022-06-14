package com.shulgin.maxi.parser;

import com.shulgin.maxi.exception.CheckParserException;
import com.shulgin.maxi.service.ProductSaleService;
import com.shulgin.maxi.service.ProductService;
import com.shulgin.maxi.service.SaleService;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SaxCheckParser {

    private ProductService productService;
    private SaleService saleService;
    private ProductSaleService productSaleService;

    public SaxCheckParser(ProductService productService,
                          SaleService saleService,
                          ProductSaleService productSaleService) {
        this.productService = productService;
        this.saleService = saleService;
        this.productSaleService = productSaleService;
    }

    public void parse(String filename) throws CheckParserException {
        Objects.requireNonNull(filename);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxParserHandler handler = new SaxParserHandler(productService, saleService, productSaleService);
        File file = new File(filename);
        SAXParser parser;
        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            throw new CheckParserException("Open sax parser error: " + e.getMessage());
        }

        try {
            parser.parse(file, handler);
        }catch (SAXException e) {
            throw new CheckParserException("Sax parsing error: " + e.getMessage());
        } catch (IOException e) {
            throw new CheckParserException("IO parsing error: " + e.getMessage());
        }
    }
}
