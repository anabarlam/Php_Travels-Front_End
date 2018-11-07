package com.pageobjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pojo.FlightDetails;

public class HomePage {
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = ".nav-tabs.RTL [data-title=flights]")
	private WebElement optnFlightsSearchBar;
	
	@FindBy(how = How.ID, using = "s2id_autogen12")
	private WebElement txtOrigin;
	
	@FindBy(how = How.CSS, using = ".select2-results>li:nth-child(1)>div")
	private WebElement optnSearchMatch;
	
	@FindBy(how = How.ID, using = "s2id_autogen13")
	private WebElement txtDestination;
	
	@FindBy(how = How.NAME, using = "departure")
	private WebElement txtDepartureDate;
	
	@FindBy(how = How.CSS, using = "#flights .pfb0")
	private WebElement btnSearch;
	
	private void enterOrigin(String origin) {
		txtOrigin.sendKeys(origin);
	}
	
	private void enterDestination(String destination) {
		txtDestination.sendKeys(destination);
	}
	
	private void enterDepartureDate() {
		LocalDate today = LocalDate.now().plus(1, ChronoUnit.DAYS);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		txtDepartureDate.sendKeys(today.format(formatter));
	}
	
	private void selectSearchResult() {
		optnSearchMatch.click();
	}
	
	private void clickSearchButton() {
		btnSearch.click();
	}
	
	public void clikFlightSearchBarOption() {
		optnFlightsSearchBar.click();
	}
	
	public WebElement getInputOriginLocator() {
		return txtOrigin;
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
