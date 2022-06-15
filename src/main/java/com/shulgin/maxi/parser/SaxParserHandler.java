package com.shulgin.maxi.parser;

import com.shulgin.maxi.entity.Product;
import com.shulgin.maxi.entity.Sale;
import com.shulgin.maxi.service.ProductSaleService;
import com.shulgin.maxi.service.ProductService;
import com.shulgin.maxi.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SaxParserHandler extends DefaultHandler {

    @Autowired
    private ProductService productService;
    @Autowired
    private SaleService saleService;
    @Autowired
    private ProductSaleService productSaleService;

    private String currentTagName;
    private boolean isSale = false;
    private boolean isCardNumber = false;
    private boolean isDate = false;
    private boolean isProducts = false;
    private boolean isProduct = false;
    private boolean isProductCode = false;
    private boolean isProductName = false;
    private boolean isProductCount = false;
    private boolean isProductPrice = false;

    private String cardNumber;

    private java.sql.Date date;
    private java.sql.Time time;

    private Long productCode;
    private String productName;
    private BigDecimal productPrice;
    private int productCount;

    HashMap<Product, Integer> products;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start document");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTagName = qName;
        if(currentTagName.equals(SaxParserTags.PRODUCTS)) {
            products = new HashMap<>();
        }
        changeTagState(currentTagName, true);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        changeTagState(qName, false);
        if(qName.equals(SaxParserTags.SALE)) {
            Sale sale = new Sale(cardNumber, date, time);
            saleService.addSale(sale);
            Map<Long, Integer> productsIdAndCount = products.entrySet()
                    .stream().collect(Collectors.toMap(
                            x -> x.getKey().getProductCode(),
                            Map.Entry::getValue
                    ));
            productSaleService.addProductSale(sale.getId(), productsIdAndCount);
        }
        if ((qName.equals(SaxParserTags.PRODUCT))) {
            Product product = new Product(productCode, productName, productPrice);
            productService.addProduct(product);
            products.put(product, products.getOrDefault(product, 0) + productCount);
        }
        currentTagName = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(currentTagName == null) {
            return;
        }
        if(isCardNumber) {
            cardNumber = new String(ch, start, length);
        }
        if (isDate) {
            long longDate = Long.parseLong(new String(ch, start, length));
            date = new Date(longDate);
            time = new Time(longDate);
        }
        if(isProductCode) {
            productCode = Long.parseLong(new String(ch, start, length));
        }
        if (isProductName) {
            productName = new String(ch, start, length);
        }
        if(isProductPrice) {
            productPrice = parseCharArrayToBigDecimal(ch, start, length);
        }
        if(isProductCount) {
            productCount = Integer.parseInt(new String(ch, start, length));
        }
    }

    private void changeTagState(String tag, boolean state) {
        if(tag != null) {
            switch (tag) {
                case SaxParserTags.SALE:
                    isSale = state;
                    break;
                case SaxParserTags.CARD_NUMBER:
                    isCardNumber = state;
                    break;
                case SaxParserTags.DATE:
                    isDate = state;
                    break;
                case SaxParserTags.PRODUCTS:
                    isProducts = state;
                    break;
                case SaxParserTags.PRODUCT:
                    isProduct = state;
                    break;
                case SaxParserTags.PRODUCT_CODE:
                    isProductCode = state;
                    break;
                case SaxParserTags.NAME:
                    isProductName = state;
                    break;
                case SaxParserTags.PRICE:
                    isProductPrice = state;
                    break;
                case SaxParserTags.COUNT:
                    isProductCount = state;
                    break;
            }
        }
    }

    private BigDecimal parseCharArrayToBigDecimal(char[] ch, int start, int length) {
        for(int i = start; i < start + length; i++) {
            if(ch[i] == ',') {
                ch[i] = '.';
            }
        }
        return new BigDecimal(ch, start, length);
    }
}
