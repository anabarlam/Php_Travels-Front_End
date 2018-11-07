package com.utils.managers;

import org.openqa.selenium.WebDriver;

import com.pageobjects.AccountPage;
import com.pageobjects.ConfirmBookingPage;
import com.pageobjects.FlightSearchResultsPage;
import com.pageobjects.HomePage;
import com.pageobjects.InvoicePage;
import com.pageobjects.LoginPage;
import com.pageobjects.HeaderNavigation;
import com.pageobjects.SearchFlightsBar;
import com.pageobjects.SignUpPage;

public class PageObjectManager {
	private WebDriver driver;
	private AccountPage accountPage;
	private SearchFlightsBar flightsPage;
	private LoginPage loginPage;
	private HeaderNavigation headerNavigation;
	private SignUpPage signUpPage;
	private FlightSearchResultsPage flightSearchResultsPage;
	private ConfirmBookingPage confirmBookingPage;
	private InvoicePage invoicePage;
	private HomePage homePage;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public AccountPage getAccountPage() {
		accountPage = (accountPage == null) ? new AccountPage(driver) : accountPage;
		return accountPage;
	}
	
	public SearchFlightsBar getFlightsPage() {
		flightsPage = (flightsPage == null) ? new SearchFlightsBar(driver) : flightsPage;
		return flightsPage;
	}
	
	public LoginPage getLoginPage() {
		loginPage = (loginPage == null) ? new LoginPage(driver) : loginPage;
		return loginPage;
	}
	
	public HeaderNavigation getHeaderNavigation() {
		headerNavigation = (headerNavigation == null) ? new HeaderNavigation(driver) : headerNavigation;
		return headerNavigation;
	}
	
	public SignUpPage getSignUpPage() {
		signUpPage = (signUpPage == null) ? new SignUpPage(driver) : signUpPage;
		return signUpPage;
	}
	
	public FlightSearchResultsPage getFlightSearchResultsPage() {
		flightSearchResultsPage = (flightSearchResultsPage == null) ? new FlightSearchResultsPage(driver) : flightSearchResultsPage;
		return flightSearchResultsPage;
	}
	
	public ConfirmBookingPage getConfirmBookingPage() {
		confirmBookingPage = (confirmBookingPage == null) ? new ConfirmBookingPage(driver) : confirmBookingPage;
		return confirmBookingPage;
	}
	
	public InvoicePage getInvoicePage() {
		invoicePage = (invoicePage == null) ? new InvoicePage(driver) : invoicePage;
		return invoicePage;
	}
	
	public HomePage getHomePage() {
		homePage = (homePage == null) ? new HomePage(driver) : homePage;
		return homePage;
	}
}
