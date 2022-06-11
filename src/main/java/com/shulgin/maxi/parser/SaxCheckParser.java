package com.shulgin.maxi.parser;

import com.shulgin.maxi.entity.Sale;

import javax.xml.parsers.SAXParserFactory;
import java.util.List;

public class SaxParser {

    public List<Sale> parse() {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SaxParserHandler sph = new SaxParserHandler();
        

        return null;
    }
}
