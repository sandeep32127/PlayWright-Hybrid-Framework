package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.MyAccountPage;

@Listeners(ExtentReportListener.class)
public class LoginPageTest extends BaseTest{
	
	public LoginPage loginPage;
	public MyAccountPage myAccountPage;
	
	@Test(priority = 1)
	public void verifyLogiPageTitle() {
		loginPage = homePage.navigateToLoginPage();
		Assert.assertEquals(loginPage.getTitle(), AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void veriftForgotPwdLink() {
		Assert.assertTrue(loginPage.verifyForgitPwdLnk());
	}
	
	@Test(priority = 3)
	public void verifyLogin() {
		myAccountPage = loginPage.doLogin(configReader.getProperty("username").trim(), configReader.getProperty("password").trim());
		Assert.assertTrue(myAccountPage.verifyLogOutBtn());
	}

}
