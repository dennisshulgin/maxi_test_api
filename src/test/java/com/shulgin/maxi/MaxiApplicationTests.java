package com.shulgin.maxi;

import com.shulgin.maxi.entity.Sale;
import com.shulgin.maxi.exception.CheckParserException;
import com.shulgin.maxi.parser.SaxCheckParser;
import com.shulgin.maxi.service.ProductSaleService;
import com.shulgin.maxi.service.ProductService;
import com.shulgin.maxi.service.SaleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MaxiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ProductService productService;
	@Autowired
	private SaleService saleService;
	@Autowired
	private ProductSaleService productSaleService;

	@Value("${check.directory}")
	String path;

	@Test
	void xmlTest() {
		List<String> fileNames;
		try {
			fileNames = Files.walk(Paths.get(path))
					.filter(Files::isRegularFile)
					.map(Path::toString)
					.filter(x -> x.endsWith(".xml"))
					.collect(Collectors.toList());
			fileNames.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
