package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	
	private Page page;
	
	private String boxSearch = "input[name='search']";
	private String btnSearch = "div#search span button";
	private String headerSearch = "div#content h1";
	private String dropDwnMyAccount = ".dropdown a[title='My Account'] span:has-text('My Account')";
	private String btnLogin = "a:text('Login')";
	
	public HomePage(Page page) {
		this.page = page;
	}
	
	public String getHomePageTitle() {
		String title = page.title();
		System.out.println("The home page title is : "+title);
		return title;
	}
	
	public String getHomePageURL() {
		String url = page.url();
		System.out.println("The home page URL is : "+url);
		return url;
	}
	
	public String doSearch(String product) {
		page.fill(boxSearch, product);
		page.click(btnSearch);
		String searchHeader = page.textContent(headerSearch);
		System.out.println("The search header is : "+searchHeader);
		return searchHeader;
	}
	
	public LoginPage navigateToLoginPage() {
		page.click(dropDwnMyAccount);
		page.click(btnLogin);
		return new LoginPage(page);
	}

}
