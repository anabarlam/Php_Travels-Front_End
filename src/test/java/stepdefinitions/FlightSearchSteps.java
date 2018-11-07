package stepdefinitions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.pageobjects.AccountPage;
import com.pageobjects.FlightSearchResultsPage;
import com.pageobjects.HeaderNavigation;
import com.pageobjects.HomePage;
import com.pageobjects.SearchFlightsBar;
import com.utils.BasePage;
import com.utils.Waits;
import com.utils.managers.FileReaderManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FlightSearchSteps {
	AccountPage accountPage;
	BasePage basePage;
	FlightSearchResultsPage flightSearchResultsPage;
	HeaderNavigation headerNavigation;
	HomePage homePage;
	SearchFlightsBar flightsPage;
	
	public FlightSearchSteps(BasePage basePage) {
		this.basePage = basePage;
		accountPage = basePage.getPageObjectManager().getAccountPage();
		flightsPage = basePage.getPageObjectManager().getFlightsPage();
		flightSearchResultsPage = basePage.getPageObjectManager().getFlightSearchResultsPage();
		headerNavigation = basePage.getPageObjectManager().getHeaderNavigation();
		homePage = basePage.getPageObjectManager().getHomePage();
	}
	
	@Given("^user is on Flights search bar$")
	public void user_is_on_Flights_search_bar() {
		homePage.clikFlightSearchBarOption();
		Waits.untilJQueryIsDone(basePage.getWebDriverManager().getDriver());
	}
	
	@When("^user searches a flight$")
	public void user_searches_a_flight() {
		try {
			if(flightsPage.getOriginLocator().isDisplayed()) {
				flightsPage.searchFlight(FileReaderManager.getInstance().getJsonReader().getFlightDetails());
			}
		} catch(org.openqa.selenium.NoSuchElementException e) {
			homePage.searchFlight(FileReaderManager.getInstance().getJsonReader().getFlightDetails());
		}
		Waits.untilJQueryIsDone(basePage.getWebDriverManager().getDriver());
	}

	@Then("^flight search results is displayed$")
	public void flight_search_results_is_displayed() {
		try {
			assertTrue("Search results did not match search criteria!", 
					flightSearchResultsPage.isSearchResultCorrect(FileReaderManager.getInstance().getJsonReader().getFlightDetails()));
		} catch (Exception e) {
			fail("Unable to display search results.");
		}
	}
}
