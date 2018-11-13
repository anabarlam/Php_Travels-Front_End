package stepdefinitions;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.constants.Literals;
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
		requiredField = (requiredField.equalsIgnoreCase(Literals.LABEL_CONFIRM_PASSWORD)) 
				? Literals.LABEL_PASSWORD : requiredField ;
		String actualError = signUpPage.getFirstSignUpError();
		String expectedError = Literals.ERR_REQUIRED_SIGN_UP_FIELD.replace("requiredField", requiredField);
		try {
			assertThat(Literals.FAIL_MSG_NOT_DISPLAYED, actualError, equalToIgnoringCase(expectedError));
		} catch (org.openqa.selenium.NoSuchElementException e) {
			fail(Literals.FAIL_MSG_NOT_DISPLAYED);
		}
	}
	
	@Then("^an error on password that (.*) is displayed$")
	public void an_error_on_password_that_is_displayed(String passwordCondition) {
		String actualError = signUpPage.getFirstSignUpError();
		String expectedError = (passwordCondition.equalsIgnoreCase(Literals.TXT_PASSWORD_LESS_THAN_SIX)) 
				? Literals.ERR_PASSWORD_LENGTH : Literals.ERR_PASSWORDS_DONT_MATCH;
		try {
			assertThat(Literals.FAIL_MSG_NOT_DISPLAYED, actualError, equalToIgnoringCase(expectedError));
		} catch (org.openqa.selenium.NoSuchElementException e) {
			fail(Literals.FAIL_MSG_NOT_DISPLAYED);
		}
	}

	@Then("^a new user account is created$")
	public void a_new_user_account_is_created() {
		Waits.untilJQueryIsDone(basePage.getWebDriverManager().getDriver());
		try {
			assertTrue(Literals.FAIL_USER_CREATION, accountPage.getGreetingLocator().isDisplayed());
		} catch (org.openqa.selenium.NoSuchElementException e) {
			fail(Literals.FAIL_USER_CREATION + signUpPage.getSignUpError());
		}
	}
	
}
