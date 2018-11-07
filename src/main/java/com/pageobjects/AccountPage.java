package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	public AccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = ".col-md-6.go-right.RTL .RTL")
	private WebElement lblGreeting;
	
	public WebElement getGreetingLocator() {
		return lblGreeting;
	}
}
