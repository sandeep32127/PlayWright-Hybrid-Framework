package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	
	private Page page;
	
	private String lnkForgotPwd = "//div[@class = 'form-group']/a[normalize-space() = 'Forgotten Password']";
	private String iputEmail = "//input[@id = 'input-email']";
	private String inputPwd = "//input[@id = 'input-password']";
	private String btnLogin = "//input[@value= 'Login']";
	
	public LoginPage(Page page) {
		this.page = page;
	}
	
	public String getTitle() {
		return page.title();
	}
	
	public boolean verifyForgitPwdLnk() {
		return page.isVisible(lnkForgotPwd);
	}
	
	public MyAccountPage doLogin(String uName, String password) {
		page.fill(iputEmail, uName);
		page.fill(inputPwd, password);
		page.click(btnLogin);
		return new MyAccountPage(page);
	}
	

}
