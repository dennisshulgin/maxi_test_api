package com.shulgin.maxi;

import com.shulgin.maxi.entity.Sale;
import com.shulgin.maxi.exception.CheckParserException;
import com.shulgin.maxi.parser.SaxCheckParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MaxiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void xmlTest() {
		SaxCheckParser saxCheckParser = new SaxCheckParser();
		List<Sale> sales = null;
		try {
			sales = saxCheckParser.parse();
		} catch (CheckParserException e) {
			e.printStackTrace();
		}
		System.out.println(sales);
	}

}
