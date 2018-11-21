@flights @reservation
Feature: Reservation
Description: This is an example of an end to end testing,
covering all the possible process path, assuring that the
application behaves as expected in accordance to a series
of system steps.

@oneWay
Scenario: Verify that a registered user can reserve a flight
	Given user is logged in to PHP Travels
	And user is on Flights page
	When user searches a flight
	And books an available schedule
	Then the flight booking status is reserved
