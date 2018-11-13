package com.readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.DriverType;
import com.constants.EnvironmentType;
import com.constants.Literals;

public class ConfigFileReader {
	private Properties properties;
	
	public ConfigFileReader() {
		try(BufferedReader reader = new BufferedReader(new FileReader(Literals.CONFIG_PROPERTY_FILE_PATH));) {
			properties = new Properties();
			properties.load(reader);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(Literals.ERR_CONFIG_FILE_NOT_FOUND_AT + Literals.CONFIG_PROPERTY_FILE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getDriverPath(){
		String driverPath = properties.getProperty("driverPath");
		if(driverPath != null) {
			return driverPath;
		} else{
			throw new RuntimeException(Literals.ERR_DRIVER_NOT_IN_CONFIG_FILE);		
		}
	}
	
	public long getImplicitWait() {		
		String implicitWait = properties.getProperty("implicitWait");
		if(implicitWait != null) {
			try {
				return Long.parseLong(implicitWait);
			} catch (NumberFormatException e) {
				throw new RuntimeException(Literals.ERR_CANT_PARSE_NUMBER + implicitWait);
			}
		}
		return 30;	
	}
	
	public String getUrl() {
		String url = properties.getProperty("url");
		if(url != null) {
			return url;
		} else {
			throw new RuntimeException(Literals.ERR_URL_NOT_IN_CONFIG_FILE);
		}
	}
	
	public DriverType getBrowser() {
		String browserName = properties.getProperty("browser").toLowerCase();
		if(browserName == null) {
			return DriverType.CHROME;
		}
		switch(browserName) {
		case "chrome":
			return DriverType.CHROME;
		case "firefox":
			return DriverType.FIREFOX;
		case "iexplorer":
			return DriverType.INTERNETEXPLORER;
		default:
			throw new RuntimeException(Literals.ERR_UNEXPECTED_BROWSER_NAME + browserName);
		}
	}
	
	public EnvironmentType getEnvironment() {
		String environmentName = properties.getProperty("environment");
		if(environmentName == null || environmentName.equalsIgnoreCase("local")) {
			return EnvironmentType.LOCAL;
		} else if(environmentName.equalsIgnoreCase("remote")) {
			return EnvironmentType.REMOTE;
		} else{
			throw new RuntimeException(Literals.ERR_UNEXPECTED_BROWSER_NAME + environmentName);
		}
	}
	
	public Boolean getBrowserWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if(windowSize != null) {
			return Boolean.valueOf(windowSize);
		} else {
			return true;
		}
	}
	
	public String getTestDataPath() {
		String testDataPath = properties.getProperty("testDataPath");
		if(testDataPath != null) {
			return testDataPath;
		} else {
			throw new RuntimeException(Literals.CONFIG_PROPERTY_FILE_PATH );
		}
	}
	
	public String getReportConfigPath() {
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if(reportConfigPath != null) {
			return reportConfigPath;
		} else {
			throw new RuntimeException(Literals.ERR_REPORT_CONFIG_NOT_IN_CONFIG_FILE);
		}
	}
}
