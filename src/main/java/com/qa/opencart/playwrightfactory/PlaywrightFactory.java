package com.qa.opencart.playwrightfactory;

import java.nio.file.Paths;
import java.util.Base64;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<Playwright>();
	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<Browser>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<BrowserContext>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<Page>();
	
	public static synchronized  Playwright getPlaywright() {
		return tlPlaywright.get();
	}
	
	public static synchronized Browser getBrowser() {
		return tlBrowser.get();
	}
	
	public static synchronized BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}
	
	public static synchronized Page getPage() {
		return tlPage.get();
	}
	
	public Page initiBrowser(String browserName, String url) {		
		System.out.println("The browser name is : "+browserName);		
		//playwright = Playwright.create();
		tlPlaywright.set(Playwright.create());
		
		switch (browserName.toLowerCase()) {
		case "chromium":
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "chrome":
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;
		case "safari":
			//browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;	
		case "firefox":
			//browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "edge":
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false)));
			break;
		
		default:
			System.out.println("Please enter a correct browser name ");
			break;
		}
		
		/*
		 * browserContext = browser.newContext(); page = browserContext.newPage();
		 * page.navigate(url); return page;
		 */
		tlBrowserContext.set(getBrowser().newContext());
		tlPage.set(getBrowserContext().newPage());
		getPage().navigate(url);
		return getPage();
	}
	
	public static String takeScreenShot() {
		String path = "./screeshots/"+ System.currentTimeMillis() + ".png";
		byte[] screenshot = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String encodeToString = Base64.getEncoder().encodeToString(screenshot);
		return encodeToString;
	}
}
