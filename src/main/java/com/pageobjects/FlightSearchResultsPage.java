package com.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pojo.FlightDetails;

public class FlightSearchResultsPage {
	public FlightSearchResultsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = "#load_data tr:nth-child(1) .bookbtn")
	private WebElement btnBookNow;
	
	@FindBy(how = How.CSS, using = "#load_data tr:nth-child(1) .col-md-4.col-sm-3.col-xs-4 span")
	private List<WebElement> tblFlightSearchResult;
	
	public WebElement getBookNowButtonLocator() {
		return btnBookNow;
	}
	
	public void clickBookNowButton() {
		btnBookNow.click();
	}
	
	public boolean isSearchResultCorrect(FlightDetails flightDetails) {
		List<String> actualResults = new ArrayList<>();
		boolean resultsMatched = true;
		
		for(WebElement element : tblFlightSearchResult) {
			actualResults.add(element.getText());
		}
		for(String actualResult : actualResults) {
			if(flightDetails.getOrigin().equalsIgnoreCase(actualResult)) {
				resultsMatched = true;
			} else if(flightDetails.getDestination().equalsIgnoreCase(actualResult)) {
				resultsMatched = true;
			} else {
				resultsMatched = false;
			}
		}
		return resultsMatched;
	}
}
