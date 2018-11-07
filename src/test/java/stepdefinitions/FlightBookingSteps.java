package stepdefinitions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.pageobjects.ConfirmBookingPage;
import com.pageobjects.FlightSearchResultsPage;
import com.pageobjects.HeaderNavigation;
import com.pageobjects.InvoicePage;
import com.utils.BasePage;
import com.utils.Waits;
import com.utils.managers.FileReaderManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FlightBookingSteps {
	BasePage basePage;
	ConfirmBookingPage confirmBookingPage;
	FlightSearchResultsPage flightSearchResultsPage;
	HeaderNavigation headerNavigation;
	InvoicePage invoicePage;
	
	public FlightBookingSteps(BasePage basePage) {
		this.basePage = basePage;
		confirmBookingPage = basePage.getPageObjectManager().getConfirmBookingPage();
		flightSearchResultsPage = basePage.getPageObjectManager().getFlightSearchResultsPage();
		headerNavigation = basePage.getPageObjectManager().getHeaderNavigation();
		invoicePage = basePage.getPageObjectManager().getInvoicePage();
	}
	
	@Given("^user is on Flights page$")
	public void user_is_on_Flights_page() {
		headerNavigation.goToFLightsPage();
	}
	
	@When("^books an available schedule$")
	public void books_an_available_schedule() {
		Waits.untilJQueryIsDone(basePage.getWebDriverManager().getDriver());
		flightSearchResultsPage.clickBookNowButton();
		Waits.untilJQueryIsDone(basePage.getWebDriverManager().getDriver());
		confirmBookingPage.clickConfirmBooking();
		Waits.untilJQueryIsDone(basePage.getWebDriverManager().getDriver());
		invoicePage.clickPayOnArrivalButton();
		basePage.getWebDriverManager().getDriver().switchTo().alert().accept();
	}
	
	@Then("^the flight booking status is reserved$")
	public void the_flight_booking_status_is_reserved() {
		String expectedBookingStatus = FileReaderManager.getInstance().getJsonReader().getFlightDetails().getStatus();
		String actualBookingStatus = invoicePage.getBookingStatus();
		Waits.untilPageIsLoaded(basePage.getWebDriverManager().getDriver());
		assertThat(actualBookingStatus, is(expectedBookingStatus));
	}
}
