package com.readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.enums.DriverType;
import com.enums.EnvironmentType;

public class ConfigFileReader {
	private Properties properties;
	
	public ConfigFileReader() {
		final String propertyFilePath = "configs//Configuration.properties";
		try(BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));) {
			properties = new Properties();
			properties.load(reader);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getDriverPath(){
		String driverPath = properties.getProperty("driverPath");
		if(driverPath != null) {
			return driverPath;
		} else{
			throw new RuntimeException("driverPath is not specified in the Configuration.properties file");		
		}
	}
	
	public long getImplicitWait() {		
		String implicitWait = properties.getProperty("implicitWait");
		if(implicitWait != null) {
			try {
				return Long.parseLong(implicitWait);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitWait + " to Long");
			}
		}
		return 30;	
	}
	
	public String getUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url is not specified in the Configuration.properties file");
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
			throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
		}
	}
	
	public EnvironmentType getEnvironment() {
		String environmentName = properties.getProperty("environment");
		if(environmentName == null || environmentName.equalsIgnoreCase("local")) {
			return EnvironmentType.LOCAL;
		} else if(environmentName.equalsIgnoreCase("remote")) {
			return EnvironmentType.REMOTE;
		} else{
			throw new RuntimeException("Environment Type Key value in Configuration.properties is not matched : " + environmentName);
		}
	}
	
	public Boolean getBrowserWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if(windowSize != null) {
			return Boolean.valueOf(windowSize);
		}
		return true;
	}
	
	public String getTestDataPath() {
		String testDataPath = properties.getProperty("testDataPath");
		if(testDataPath!= null) return testDataPath;
		else throw new RuntimeException("Test Data Path not specified in Configuration.properties file for Key:testDataResourcePath");
	}
	
	public String getReportConfigPath() {
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if(reportConfigPath!=null) {
			return reportConfigPath;
		}
		else throw new RuntimeException("Report Config Path is not specified in Configuration.properties file");
	}
}
