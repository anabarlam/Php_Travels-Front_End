package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

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
		confirmPassword(userAccount.getPassword());
		clickSignUpButton();
	}
	
	public String getSignUpError() {
		return txtSignUpError.getText();
	}
}
