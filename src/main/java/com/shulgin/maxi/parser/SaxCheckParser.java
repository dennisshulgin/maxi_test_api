package com.shulgin.maxi.parser;

import com.shulgin.maxi.exception.CheckParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
public class SaxCheckParser {

    @Autowired
    SaxParserHandler handler;

    public void parse(String filename) throws CheckParserException {
        Objects.requireNonNull(filename);
        SAXParserFactory factory = SAXParserFactory.newInstance();
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
