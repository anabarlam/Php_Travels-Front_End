package com.utils;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Literals;
import com.utils.managers.FileReaderManager;

public class Waits {
	public static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition){
		until(driver, waitCondition, FileReaderManager.getInstance().getConfigReader().getImplicitWait());
	}
	
	@SuppressWarnings("deprecation")
	private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
		try {
			webDriverWait.until(waitCondition);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void untilJQueryIsDone(WebDriver driver){
		untilJQueryIsDone(driver, FileReaderManager.getInstance().getConfigReader().getImplicitWait());
	}
	
	private static void untilJQueryIsDone(WebDriver driver, Long timeoutInSeconds) {
		until(driver, function ->
		{
			Boolean isJQueryCallDone = (Boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
			if(!isJQueryCallDone) {
				System.out.println(Literals.WAIT_JQUERY_IN_PROGRESS);
			}
			return isJQueryCallDone;
		}, timeoutInSeconds);
	}
	
	public static void untilPageIsLoaded(WebDriver driver) {
		untilPageIsLoaded(driver, FileReaderManager.getInstance().getConfigReader().getImplicitWait());
	}
	
	private static void untilPageIsLoaded(WebDriver driver, Long timeoutInSeconds) {
		until(driver, d ->
		{
			Boolean isPageLoaded = (Boolean) ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			if(!isPageLoaded) {
				System.out.println(Literals.WAIT_DOCUMENT_LOADING);
			}
			return isPageLoaded;
		}, timeoutInSeconds);
	}
}
