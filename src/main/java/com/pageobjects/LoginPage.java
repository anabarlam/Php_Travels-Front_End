package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pojo.UserAccount;

public class LoginPage {
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.NAME, using = "username")
	private WebElement txtEmail;
	
	@FindBy(how = How.NAME, using = "password")
	private WebElement txtPassword;
	
	@FindBy(how = How.CSS, using = ".loginbtn")
	private WebElement btnLogin;
	
	private void enterEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	private void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	private void clickLoginButton() {
		btnLogin.click();
	}
	
	public void login(UserAccount account) {
		enterEmail(account.getEmail());
		enterPassword(account.getPassword());
		clickLoginButton();
	}
}
