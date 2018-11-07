package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class InvoicePage {
	public InvoicePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = ".arrivalpay")
	private WebElement btnPayOnArrival;
	
	@FindBy(how = How.CSS, using = ".animted.animated.animated")
	private WebElement lblBookingStatus;
	
	public WebElement getPayOnArrivalButtonLocator() {
		return btnPayOnArrival;
	}
	
	public void clickPayOnArrivalButton() {
		btnPayOnArrival.click();
	}
	
	public String getBookingStatus() {
		return lblBookingStatus.getText();
	}
}
