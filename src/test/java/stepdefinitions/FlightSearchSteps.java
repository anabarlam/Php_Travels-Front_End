package stepdefinitions;

import com.pageobjects.HomePage;
import com.pageobjects.SearchFlightsBar;
import com.utils.BasePage;
import com.utils.Waits;
import com.utils.managers.FileReaderManager;

import cucumber.api.java.en.When;

public class FlightSearchSteps {
	BasePage basePage;
	HomePage homePage;
	SearchFlightsBar flightsPage;
	
	public FlightSearchSteps(BasePage basePage) {
		this.basePage = basePage;
		flightsPage = basePage.getPageObjectManager().getFlightsPage();
		homePage = basePage.getPageObjectManager().getHomePage();
	}
	
	@When("^user searches a flight$")
	public void user_searches_a_flight() {
		try {
			flightsPage.searchFlight(FileReaderManager.getInstance().getJsonReader().getFlightDetails());
		} catch(org.openqa.selenium.NoSuchElementException e) {
			homePage.searchFlight(FileReaderManager.getInstance().getJsonReader().getFlightDetails());
		}
		Waits.untilJQueryIsDone(basePage.getWebDriverManager().getDriver());
	}
}
