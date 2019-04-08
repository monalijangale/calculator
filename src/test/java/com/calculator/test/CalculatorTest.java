package com.calculator.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.calculator.pages.CalculatorPage;
import com.calculator.pages.CalculatorPage.Operation;
import com.calculator.webdriver.WebdriverBase;

public class CalculatorTest {

	@BeforeSuite
	public void start() {
		WebdriverBase base = new WebdriverBase();
		base.setDriver();
	}

	@DataProvider(name = "TestData")
	public static Object[][] testData() {

		return new Object[][] { 
			{ 423, 525, Operation.Mult, 222075 }, 
			{ 4000, 200, Operation.Div, 20 },
			{ -234234, 345345, Operation.Plus, 111111 },
			{ 234823,-23094823, Operation.Minus, 23329646 } };
	}

	@Test(dataProvider = "TestData")
	public void testCalculator(long number1, long number2, Operation operation, long result) {
		CalculatorPage page = new CalculatorPage();
		page.openApp();
		page.calculate(number1, number2, operation);
		assertEquals(page.getResult(), result,
				"Result not equal to expected. Expected= " + result + "Actual=" + page.getResult());
	}

	@AfterSuite
	public void quit() {
		WebdriverBase base = new WebdriverBase();
		base.quit();
	}

}
