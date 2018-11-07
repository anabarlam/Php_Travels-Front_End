package com.utils.managers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.enums.DriverType;
import com.enums.EnvironmentType;

public class WebDriverManager {
	private WebDriver driver;
	private static DriverType driverType;
	private static EnvironmentType environmentType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	
	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
	}
	
	private WebDriver createLocalDriver() {
        switch (driverType) {	    
        case FIREFOX :
        	driver = new FirefoxDriver();
	    	break;
        case CHROME : 
        	System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getDriverPath());
        	driver = new ChromeDriver();
    		break;
        case INTERNETEXPLORER :
        	driver = new InternetExplorerDriver();
    		break;
        }
        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) {
        	driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWait(), TimeUnit.SECONDS);
		return driver;
	}	
	
	private WebDriver createRemoteDriver() {
		throw new RuntimeException("RemoteWebDriver is not yet implemented");
	}
	
	public WebDriver createDriver() {
		if(environmentType == EnvironmentType.LOCAL) {
			driver = createLocalDriver();
		} else if(environmentType == EnvironmentType.REMOTE) {
			driver = createRemoteDriver();
		}
		return driver;
	}
	
	public WebDriver getDriver() {
		driver = (driver == null) ? createDriver() : driver;
		return driver;
	}
	
	public void quitDriver() {
		driver.close();
		driver.quit();
	}
}
