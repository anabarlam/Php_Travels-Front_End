Feature: Flight Searching

@flightSearch
Scenario: Verify that a guest user can search flights
	Given user is on Flights search bar
	When user searches a flight
	Then flight search results is displayed
	