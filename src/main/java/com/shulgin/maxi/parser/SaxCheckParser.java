package com.shulgin.maxi.parser;

import com.shulgin.maxi.entity.Sale;
import com.shulgin.maxi.exception.CheckParserException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaxCheckParser {

    public List<Sale> parse() throws CheckParserException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxParserHandler handler = new SaxParserHandler();
        File file = new File("check.xml");
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
        return handler.getSalesList();
    }
}
