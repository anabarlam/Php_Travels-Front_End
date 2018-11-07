package com.utils;

import com.utils.managers.PageObjectManager;
import com.utils.managers.WebDriverManager;

public class BasePage {
	private WebDriverManager webDriverManager;
	private PageObjectManager pageObjectManager;
	
	public BasePage() {
		webDriverManager = new WebDriverManager();
		pageObjectManager = new PageObjectManager(webDriverManager.getDriver());
	}
	
	public WebDriverManager getWebDriverManager() {
		return webDriverManager;
	}
	
	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}
}
