package com.shulgin.maxi.parser;

import com.shulgin.maxi.entity.Product;
import com.shulgin.maxi.entity.Sale;
import com.shulgin.maxi.util.SaleUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaxParserHandler extends DefaultHandler {

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
    private long date;

    private Long productCode;
    private String productName;
    private BigDecimal productPrice;
    private int productCount;

    List<Sale> sales = new ArrayList<>();
    List<Product> products;

    public List<Sale> getSalesList() {
        return sales;
    }

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
            products = new ArrayList<>();
        }
        changeTagState(currentTagName, true);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        changeTagState(qName, false);
        if(qName.equals(SaxParserTags.SALE)) {
            Sale sale = new Sale(cardNumber, date, products);
            sales.add(sale);
        }
        if ((qName.equals(SaxParserTags.PRODUCT))) {
            products.add(new Product(productCode, productName, productCount, productPrice));
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
            date = Long.parseLong(new String(ch, start, length));
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
