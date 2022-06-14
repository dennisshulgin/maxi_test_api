package com.shulgin.maxi;

import com.shulgin.maxi.entity.Sale;
import com.shulgin.maxi.exception.CheckParserException;
import com.shulgin.maxi.parser.SaxCheckParser;
import com.shulgin.maxi.service.ProductSaleService;
import com.shulgin.maxi.service.ProductService;
import com.shulgin.maxi.service.SaleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

	@Test
	void xmlTest() {
		SaxCheckParser saxCheckParser = new SaxCheckParser(productService, saleService, productSaleService);
		try {
			//sales = saxCheckParser.parse(null);
			saxCheckParser.parse("check.xml");
		} catch (CheckParserException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
		try {
			Date date = sdf.parse("05-06-2018");
			System.out.println(productSaleService.sumAllSalesByDate(new java.sql.Date(date.getTime())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		productService.findTopProductsByCardName("78483", 5)
				.forEach(System.out::println);
	}
}
