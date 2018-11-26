package com.utils;

import com.constants.Literals;

public class ExceptionHandler {
	public static void generalExceptionHandler(Exception e) {
		throw new RuntimeException(e.getMessage());
	}
	
	public static void fileNotFoundHandler(String filePath) {
		throw new RuntimeException(Literals.FILE_NOT_FOUND_AT + filePath);
	}
	
	public static void numberFormatHandler(String number) {
		throw new RuntimeException(Literals.ERR_CANT_PARSE_NUMBER + number);
	}
	
	public static void unexpectedConfigValueHandler(String key, String value) {
		throw new RuntimeException(Literals.ERR_UNEXPECTED_CONFIG_VALUE + key + "=" + value);
	}
	
	public static void variableNotInConfigFile(String variable) {
		throw new RuntimeException(variable + Literals.ERR_IS_NOT_IN_CONFIG_FILE);
	}
}
