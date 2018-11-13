@accounts
Feature: Accounts

Background:
    Given user is on Sign up page
    
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

Scenario Outline: Verify password restrictions upon account creation
	When user creates a new account with password that <passwordCondition>
	Then an error on password that <passwordCondition> is displayed
	Examples:
	| passwordCondition 			|
	| do not match					|
	| is less than six characters	|    
    
Scenario: Verify user can create a new account
	When user creates a new account
	Then a new user account is created