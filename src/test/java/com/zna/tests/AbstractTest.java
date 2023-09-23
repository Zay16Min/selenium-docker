package com.zna.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractTest {
	protected WebDriver driver;
	
	@BeforeTest
	public void setDriver() throws MalformedURLException {
		if(Boolean.getBoolean("selenium.grid.enabled")) {
			this.driver = getRemotedDriver();
		} else {
			this.driver = getLocalDriver();
		}
	}

	private WebDriver getRemotedDriver() throws MalformedURLException {
		
		Capabilities capabilities;
		if(System.getProperty("browser").equalsIgnoreCase("chrome")) {
			capabilities = new ChromeOptions();
		} else {
			capabilities = new FirefoxOptions();
		}
		return new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);
	}
	
	private WebDriver getLocalDriver() {
		WebDriverManager.chromedriver().setup();		
		return new ChromeDriver();
	}

	@AfterTest
	public void quitDriver() {
		this.driver.quit();
	}
}
