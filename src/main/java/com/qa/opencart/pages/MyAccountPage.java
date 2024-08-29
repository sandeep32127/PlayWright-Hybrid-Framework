package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class MyAccountPage {
	private Page page;
	
	private String btnLogout = ".list-group a:text('Logout')";
	
	public MyAccountPage(Page page) {
		this.page = page;
	}
	
	public String getTitle() {
		return page.title();
	}
	
	public boolean verifyLogOutBtn() {
		return page.isVisible(btnLogout);
	}

}
