package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pojo.FlightDetails;

public class SearchFlightsBar {
	public SearchFlightsBar(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "s2id_autogen11")
	private WebElement txtOrigin;
	
	@FindBy(how = How.CSS, using = ".select2-results>li:nth-child(1)>div")
	private WebElement optnSearchMatch;
	
	@FindBy(how = How.ID, using = "s2id_autogen12")
	private WebElement txtDestination;
	
	@FindBy(how = How.NAME, using = "departure")
	private WebElement txtDepartureDate;
	
	@FindBy(how = How.CSS, using = "#flights .btn-lg.btn-block.pfb0")
	private WebElement btnSearch;
	
	@FindBy(how = How.CSS, using = ".datepicker.dropdown-menu[style] .day.active")
	private WebElement datePckrToday;
	
	private void enterOrigin(String origin) {
		txtOrigin.sendKeys(origin);
	}
	
	public WebElement getOriginLocator() {
		return txtOrigin;
	}
	
	private void selectSearchResult() {
		optnSearchMatch.click();
	}
	
	private void enterDestination(String destination) {
		txtDestination.sendKeys(destination);
	}
	
	private void enterDepartureDate() {
		txtDepartureDate.click();
		datePckrToday.click();
	}
	
	private void clickSearchButton() {
		btnSearch.click();
	}
	
	public void searchFlight(FlightDetails flightDetails) {
		enterOrigin(flightDetails.getOrigin());
		selectSearchResult();
		enterDestination(flightDetails.getDestination());
		selectSearchResult();
		enterDepartureDate();
		clickSearchButton();
	}
}
