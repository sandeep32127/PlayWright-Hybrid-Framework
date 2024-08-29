package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class HomePageTest extends BaseTest{
		
	@Test
	public void verifyHomePageTitle() {
		String actualTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
	}
	
	@Test
	public void verifyHomePageURL() {
		String actualURL = homePage.getHomePageURL();
		Assert.assertEquals(actualURL, configReader.getProperty("url"));
	}
	
	@Test(dataProvider = "productNames")
	public void verifySearch(String product) {
		String actualSearchHeader = homePage.doSearch(product);
		Assert.assertEquals(actualSearchHeader, "Search - "+product);
	}
	
	@DataProvider(name = "productNames")
	public Object[][] getProducts() {
		Object[][] data = new Object[][] {
			{"MacBook"},
			{"Iphone"},
			{"Canon"}
		};
		return data;
	}
}
