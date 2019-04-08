package com.calculator.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebdriverBase {
	public static WebDriver driver;
	
	public static WebDriver getDriver() {
		return driver;
	}

	public void setDriver() {
		System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
		if (driver == null) {
			driver = new ChromeDriver();
		}

	}
	
	public void quit(){
		driver.close();
		driver.quit();
	}
}
