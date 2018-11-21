@accounts @signup
Feature: Sign Up
Description: This is an example of a functional testing, covering both the
happy path and the negative scenarios, assuring that all the application
requirements are met and the system behaves as expected.

Background:
    Given user is on Sign up page

@negative
Scenario Outline: Verify required fields when creating a new account
	When user creates a new account without <signUpField>
	Then an error requiring <signUpField> is displayed
	Examples:
	| signUpField		|
	| first name  		|
	| last name   		|
	| email		  		|
	| password			|
	| confirm password	|

@negative
Scenario Outline: Verify password restrictions upon account creation
	When user creates a new account with password that <passwordCondition>
	Then an error on password that <passwordCondition> is displayed
	Examples:
	| passwordCondition 			|
	| do not match					|
	| is less than six characters	|    

@happyPath
Scenario: Verify user can create a new account
	When user creates a new account
	Then a new user account is created