package com.calculator.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.calculator.webdriver.WebdriverBase;

public class CalculatorPage {

	public enum Operation {
		Plus, Minus, Calc, Div, Mult
	}

	public CalculatorPage() {
		WebdriverBase base = new WebdriverBase();
		base.setDriver();
	}

	/**
	 * Open Calculator app
	 */
	public void openApp() {
		WebDriver driver = WebdriverBase.getDriver();
		driver.get("https://web2.0calc.com/");
		driver.manage().window().maximize();
		try {
			driver.findElement(By.cssSelector(".btn-info.cookiesok")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Cookie info button not visible skipping");
		}
	}

	/**
	 * method to calculate given two numbers
	 */
	public void calculate(long number1, long number2, Operation operation) {
		clickNumber(number1);
		clickOperator(operation);
		clickNumber(number2);
		WebDriver driver = WebdriverBase.getDriver();
		driver.findElement(By.id("BtnCalc")).click();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * click on operator btn
	 */
	private void clickOperator(Operation operation) {
		String opn = "Btn" + operation;
		WebdriverBase.getDriver().findElement(By.id(opn)).click();

	}

	/**
	 * click on number
	 * @param number
	 */
	private void clickNumber(long number) {
		System.out.println(number);
		int index = 0;
		if (number < 0) {
			WebdriverBase.getDriver().findElement(By.id("BtnMinus")).click();
			index = 1;
		}
		int[] intTab = String.valueOf(number).chars().map(Character::getNumericValue).toArray();
		for (int i = index; i < intTab.length; i++) {
			if (intTab[i] == -1) {
				WebdriverBase.getDriver().findElement(By.id("BtnDot")).click();
			} else {

				String id = "Btn" + intTab[i];
				System.out.println(id);
				WebdriverBase.getDriver().findElement(By.id(id)).click();
			}
		}

	}

	/**
	 * get result
	 * @return output
	 */
	public long getResult() {
		String output = WebdriverBase.getDriver().findElement(By.id("input")).getAttribute("value");
		return Long.parseLong(output);
	}

}
