package stepdefinitions;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.pageobjects.HeaderNavigation;
import com.pageobjects.SignUpPage;
import com.utils.BasePage;
import com.utils.managers.FileReaderManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	BasePage basePage;
	HeaderNavigation headerNavigation;
	SignUpPage signUpPage;
	
	public Hooks(BasePage basePage) {
		this.basePage = basePage;
		headerNavigation = basePage.getPageObjectManager().getHeaderNavigation();
		signUpPage = basePage.getPageObjectManager().getSignUpPage();
	}
	
	@Before
	public void beforeHooks() {
		basePage.getWebDriverManager().getDriver().get(FileReaderManager.getInstance().getConfigReader().getUrl());
	}
	
	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		if(scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				// This takes a screenshot from the driver and save it to a specified location
				File sourcePath = ((TakesScreenshot) basePage.getWebDriverManager().getDriver()).getScreenshotAs(OutputType.FILE);
				
				// Building up the destination path for the screenshot to save
				// Make sure to create a folder 'screenshots' with in the cucumber-reports folder
				File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".jpg");
				
				// Copy taken screenshot from source location to destination location
				Files.copy(sourcePath, destinationPath);
				
				// Attach the specified screenshot to the test
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@After(order = 0)
	public void afterHooks() {
		basePage.getWebDriverManager().quitDriver();
	}
}
