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

    @Value("${check.old.directory}")
    private String to;

    @Autowired
    SaxCheckParser saxCheckParser;

    @Scheduled(fixedDelay = 10 * 60 * 1000, initialDelay = 1000)
    public void parse() {
        List<File> files;
        try {
            files = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(x -> x.getName().endsWith(".xml"))
                    .collect(Collectors.toList());
            for (File file : files) {
                saxCheckParser.parse(file);
                String filename = file.getName();
                Files.move(Paths.get(file.getPath()), Paths.get(to + "/" + filename));
            }
        } catch (IOException | CheckParserException e) {
            e.printStackTrace();
        }
    }
}
