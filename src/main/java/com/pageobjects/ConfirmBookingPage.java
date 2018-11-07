package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ConfirmBookingPage {
	public ConfirmBookingPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = ".btn-block.completebook")
	private WebElement btnConfirmBooking;
	
	public WebElement getConfirmBookingButtonLocator() {
		return btnConfirmBooking;
	}
	
	public void clickConfirmBooking() {
		btnConfirmBooking.click();
	}
}
