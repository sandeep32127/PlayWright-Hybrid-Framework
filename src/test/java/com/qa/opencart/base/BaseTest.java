package com.qa.opencart.base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Page;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.playwrightfactory.PlaywrightFactory;
import com.qa.opencart.utils.ConfigReader;

public class BaseTest {
	
	PlaywrightFactory playwrightFactory;
	Page page;
	protected ConfigReader configReader;
	protected HomePage homePage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserNameXML) {
		configReader = new ConfigReader();
		String browserName;
		if(browserNameXML.isEmpty()) {
			browserName = configReader.getProperty("browser").trim();
		}else browserName = browserNameXML;
		String url = configReader.getProperty("url").trim();
		playwrightFactory = new PlaywrightFactory();
		page = playwrightFactory.initiBrowser(browserName,url);
		homePage = new HomePage(page);
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		page.context().browser().close();
	}
}
