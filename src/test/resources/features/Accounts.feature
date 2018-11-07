Feature: Accounts

@accounts
Scenario: Verify user can create a new account
	Given user is on Sign up page
	When user creates a new account
	Then a new user account is created