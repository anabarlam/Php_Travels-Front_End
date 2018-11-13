package stepdefinitions;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.pageobjects.AccountPage;
import com.pageobjects.HeaderNavigation;
import com.pageobjects.LoginPage;
import com.pageobjects.SignUpPage;
import com.utils.BasePage;
import com.utils.Waits;
import com.utils.managers.FileReaderManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AccountsSteps {
	AccountPage accountPage;
	BasePage basePage;
	HeaderNavigation headerNavigation;
	LoginPage loginPage;
	SignUpPage signUpPage;
	
	public AccountsSteps(BasePage basePage) {
		this.basePage = basePage;
		accountPage = basePage.getPageObjectManager().getAccountPage();
		headerNavigation = basePage.getPageObjectManager().getHeaderNavigation();
		loginPage = basePage.getPageObjectManager().getLoginPage();
		signUpPage = basePage.getPageObjectManager().getSignUpPage();
	}
	
	@Given("^user is logged in to PHP Travels$")
	public void user_is_on_PHP_Travels_Home_page() {
		headerNavigation.goToLoginPage();
		loginPage.login(FileReaderManager.getInstance().getJsonReader().getUserAccount());
		Waits.untilPageIsLoaded(basePage.getWebDriverManager().getDriver());
		try {
			if(accountPage.getGreetingLocator().isDisplayed()) {
				return;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			headerNavigation.goToSignUpPage();
			signUpPage.signUp(FileReaderManager.getInstance().getJsonReader().getUserAccount());
			Waits.untilJQueryIsDone(basePage.getWebDriverManager().getDriver());
		}
	}
	
	@Given("^user is on Sign up page$")
	public void user_is_on_Sign_up_page() {
		headerNavigation.goToSignUpPage();
	}

	@When("^user creates a new account without (.*)$")
	public void user_creates_a_new_account_without(String signUpField) {
		Waits.untilPageIsLoaded(basePage.getWebDriverManager().getDriver());
		signUpPage.signUpWithIncompleteField(signUpField, FileReaderManager.getInstance().getJsonReader().getUserAccount());
	}
	
	@When("^user creates a new account with password that (.*)$")
	public void user_creates_a_new_account_with_confirmation_password_that(String passwordCondition) {
		Waits.untilPageIsLoaded(basePage.getWebDriverManager().getDriver());
		signUpPage.signUpWithPasswordConditions(passwordCondition, FileReaderManager.getInstance().getJsonReader().getUserAccount(),
				FileReaderManager.getInstance().getJsonReader().getPasswords());
	}
	
	@When("^user creates a new account$")
	public void user_creates_a_new_account() {
		Waits.untilJQueryIsDone(basePage.getWebDriverManager().getDriver());
		signUpPage.signUp(FileReaderManager.getInstance().getJsonReader().getUserAccount());
	}
	
	@Then("^an error requiring (.*) is displayed$")
	public void an_error_requiring_is_displayed(String requiredField){
		requiredField = (requiredField.equalsIgnoreCase("confirm password")) 
				? "password" : requiredField ;
		String actualError = signUpPage.getFirstSignUpError();
		String expectedError = "The requiredField field is required.".replace("requiredField", requiredField);
		try {
			assertThat("Error message is not displayed.", actualError, equalToIgnoringCase(expectedError));
		} catch (org.openqa.selenium.NoSuchElementException e) {
			fail("Error message is not displayed.");
		}
	}
	
	@Then("^an error on password that (.*) is displayed$")
	public void an_error_on_password_that_is_displayed(String passwordCondition) {
		String actualError = signUpPage.getFirstSignUpError();
		String expectedError = (passwordCondition.equalsIgnoreCase("is less than six characters")) 
				? "The Password field must be at least 6 characters in length." : "Password not matching with confirm password.";
		try {
			assertThat("Error message is not displayed.", actualError, equalToIgnoringCase(expectedError));
		} catch (org.openqa.selenium.NoSuchElementException e) {
			fail("Error message is not displayed.");
		}
	}

	@Then("^a new user account is created$")
	public void a_new_user_account_is_created() {
		String failMessage = "Failed to create new user account.";
		Waits.untilJQueryIsDone(basePage.getWebDriverManager().getDriver());
		try {
			assertTrue(failMessage, accountPage.getGreetingLocator().isDisplayed());
		} catch (org.openqa.selenium.NoSuchElementException e) {
			fail(failMessage + signUpPage.getSignUpError());
		}
	}
	
}
