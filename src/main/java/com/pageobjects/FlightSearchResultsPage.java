package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FlightSearchResultsPage {
	public FlightSearchResultsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = "#load_data tr:nth-child(1) .bookbtn")
	private WebElement btnBookNow;
	
	public WebElement getBookNowButtonLocator() {
		return btnBookNow;
	}
	
	public void clickBookNowButton() {
		btnBookNow.click();
	}
}
