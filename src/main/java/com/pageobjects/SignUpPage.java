package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.constants.Literals;

import pojo.Passwords;
import pojo.UserAccount;

public class SignUpPage {
	public SignUpPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.NAME, using = "firstname")
	private WebElement txtFirstName;
	
	@FindBy(how = How.NAME, using = "lastname")
	private WebElement txtLastName;
	
	@FindBy(how = How.NAME, using = "email")
	private WebElement txtEmail;
	
	@FindBy(how = How.NAME, using = "password")
	private WebElement txtPassword;
	
	@FindBy(how = How.NAME, using = "confirmpassword")
	private WebElement txtConfirmPassword;
	
	@FindBy(how = How.CSS, using = ".signupbtn.btn_full.btn.btn-action.btn-block.btn-lg")
	private WebElement btnSignUp;
	
	@FindBy(how = How.CSS, using = ".alert.alert-danger>p")
	private WebElement txtFirstSignUpError;
	
	@FindBy(how = How.CSS, using = ".alert.alert-danger")
	private WebElement txtSignUpError;
	
	private void enterFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}
	
	private void enterLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}
	
	private void enterEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	private void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	private void confirmPassword(String password) {
		txtConfirmPassword.sendKeys(password);
	}
	
	private void clickSignUpButton() {
		btnSignUp.click();
	}
	
	public void signUp(UserAccount userAccount) {
		enterFirstName(userAccount.getFirstName());
		enterLastName(userAccount.getLastName());
		enterEmail(userAccount.getEmail());
		enterPassword(userAccount.getPassword());
		confirmPassword(userAccount.getConfirmPassword());
		clickSignUpButton();
	}
	
	public void signUpWithIncompleteField(String signUpWithout, UserAccount userAccount) {
		switch(signUpWithout) {
		case Literals.LABEL_FIRST_NAME:
			userAccount.setFirstName(Literals.EMPTY_STRING_VALUE);
			break;
		case Literals.LABEL_LAST_NAME:
			userAccount.setLastName(Literals.EMPTY_STRING_VALUE);
			break;
		case Literals.LABEL_EMAIL:
			userAccount.setEmail(Literals.EMPTY_STRING_VALUE);
			break;
		case Literals.LABEL_PASSWORD:
			userAccount.setPassword(Literals.EMPTY_STRING_VALUE);
			break;
		case Literals.LABEL_CONFIRM_PASSWORD:
			userAccount.setConfirmPassword(Literals.EMPTY_STRING_VALUE);
		default:
		}
		signUp(userAccount);
	}
	
	public void signUpWithPasswordConditions(String passwordCondition, 
			UserAccount userAccount, Passwords password) {
		String passwordValue = Literals.EMPTY_STRING_VALUE;
		String confirmPasswordValue = Literals.EMPTY_STRING_VALUE;
		switch(passwordCondition) {
		case Literals.TXT_PASSWORD_DO_NOT_MATCH:
			passwordValue = password.getNonMatching().getPassword();
			confirmPasswordValue = password.getNonMatching().getConfirmPassword();
			break;
		case Literals.TXT_PASSWORD_LESS_THAN_SIX:
			passwordValue = password.getLessThanSix().getPassword();
			confirmPasswordValue = password.getLessThanSix().getConfirmPassword();
			break;
		default:
		}
		userAccount.setPassword(passwordValue);
		userAccount.setConfirmPassword(confirmPasswordValue);
		signUp(userAccount);
	}
	
	public String getSignUpError() {
		return txtSignUpError.getText();
	}
	
	public String getFirstSignUpError() {
		return txtFirstSignUpError.getText();
	}
}
