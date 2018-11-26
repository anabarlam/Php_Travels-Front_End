package com.readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.DriverType;
import com.constants.EnvironmentType;
import com.constants.Literals;
import com.utils.ExceptionHandler;

public class ConfigFileReader {
	private Properties properties;
	
	public ConfigFileReader() {
		try(BufferedReader reader = new BufferedReader(new FileReader(Literals.CONFIG_PROPERTY_FILE_PATH));) {
			properties = new Properties();
			properties.load(reader);
		} catch (FileNotFoundException e) {
			ExceptionHandler.fileNotFoundHandler(Literals.CONFIG_PROPERTY_FILE_PATH);
		} catch (IOException e) {
			ExceptionHandler.generalExceptionHandler(e);
		}
	}
	
	public String getDriverPath(){
		String driverPathKey = "driverPath";
		String driverPathValue = properties.getProperty(driverPathKey);
		if(driverPathValue == null) {
			ExceptionHandler.variableNotInConfigFile(driverPathKey);
		}
		return driverPathValue;
	}
	
	public long getImplicitWait() {		
		String implicitWait = properties.getProperty("implicitWait");
		if(implicitWait != null) {
			try {
				return Long.parseLong(implicitWait);
			} catch (NumberFormatException e) {
				ExceptionHandler.numberFormatHandler(implicitWait);
			}
		}
		return 30;
	}
	
	public String getUrl() {
		String urlKey = "url";
		String urlValue = properties.getProperty(urlKey);
		if(urlValue == null) {
			ExceptionHandler.variableNotInConfigFile(urlKey);
		}
		return urlValue;
	}
	
	public DriverType getBrowser() {
		String browserNameKey = "browser";
		String browserNameValue = properties.getProperty(browserNameKey);
		DriverType driverType = null;
		
		if(browserNameValue == null) {
			return DriverType.CHROME;
		}
		switch(browserNameValue.toLowerCase()) {
		case "chrome":
			driverType = DriverType.CHROME;
			break;
		case "firefox":
			driverType = DriverType.FIREFOX;
			break;
		case "iexplorer":
			driverType = DriverType.INTERNETEXPLORER;
			break;
		default:
			ExceptionHandler.unexpectedConfigValueHandler(browserNameKey, browserNameValue);
		}
		
		return driverType;
	}
	
	public EnvironmentType getEnvironment() {
		String environmentNameKey = "environment";
		String environmentNameValue = properties.getProperty(environmentNameKey);
		EnvironmentType environmentType = null;
		
		if(environmentNameValue == null || environmentNameValue.equalsIgnoreCase("local")) {
			environmentType = EnvironmentType.LOCAL;
		} else if(environmentNameValue.equalsIgnoreCase("remote")) {
			environmentType = EnvironmentType.REMOTE;
		} else{
			ExceptionHandler.unexpectedConfigValueHandler(environmentNameKey, environmentNameValue);
		}
		return environmentType;
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
		String testDataPathKey = "testDataPath";
		String testDataPathValue = properties.getProperty(testDataPathKey);
		if(testDataPathValue == null) {
			ExceptionHandler.variableNotInConfigFile(testDataPathKey);
		}
		return testDataPathValue;
	}
	
	public String getReportConfigPath() {
		String reportConfigPathKey = "reportConfigPath";
		String reportConfigPathValue = properties.getProperty(reportConfigPathKey);
		if(reportConfigPathValue == null) {
			ExceptionHandler.variableNotInConfigFile(reportConfigPathKey);
		}
		return reportConfigPathValue;
	}
}
