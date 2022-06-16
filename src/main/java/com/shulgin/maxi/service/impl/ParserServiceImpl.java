package com.shulgin.maxi.service.impl;

import com.shulgin.maxi.exception.CheckParserException;
import com.shulgin.maxi.parser.SaxCheckParser;
import com.shulgin.maxi.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
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

    @Value("${oldcheck.directory}")
    private String to;

    @Autowired
    SaxCheckParser saxCheckParser;

    @Scheduled(fixedDelay = 10 * 60 * 1000, initialDelay = 1000)
    public void parse() {
        List<String> fileNames;
        try {
            fileNames = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(x -> x.endsWith(".xml"))
                    .collect(Collectors.toList());
            for (String name : fileNames) {
                saxCheckParser.parse(name);
                String filename = new File(name).getName();
                System.out.println(to + "/" + filename);
                Files.move(Paths.get(name), Paths.get(to + "/" + filename));
            }
        } catch (IOException | CheckParserException e) {
            e.printStackTrace();
        }
    }
}
