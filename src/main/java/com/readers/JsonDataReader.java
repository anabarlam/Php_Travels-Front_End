package com.readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.constants.Literals;
import com.utils.managers.FileReaderManager;

import gherkin.deps.com.google.gson.Gson;
import pojo.FlightDetails;
import pojo.Passwords;
import pojo.UserAccount;

public class JsonDataReader <T> {
	private final String testDataFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataPath();
	
	@SuppressWarnings("unchecked")
	public UserAccount getUserAccount() {
		return (UserAccount) getJsonData((Class<T>) UserAccount.class, testDataFilePath + "ValidUserAccount.json");
	}
	
	@SuppressWarnings("unchecked")
	public Passwords getPasswords() {
		return (Passwords) getJsonData((Class<T>) Passwords.class, testDataFilePath + "Passwords.json");
	}
	
	@SuppressWarnings("unchecked")
	public FlightDetails getFlightDetails() {
		return (FlightDetails) getJsonData((Class<T>) FlightDetails.class, testDataFilePath + "FlightDetails.json");
	}
	
	private T getJsonData(Class<T> theClass, String filePath) {
		Gson gson = new Gson();
		T data = null;
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath));) {
			data = gson.fromJson(reader, theClass);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(Literals.ERR_JSON_FILE_NOT_FOUND_AT + testDataFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
