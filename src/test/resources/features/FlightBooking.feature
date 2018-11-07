Feature: Flight Booking

@signup @flightBooking
Scenario: Verify that a registered user can reserve a flight
	Given user is logged in to PHP Travels
	And user is on Flights page
	When user searches a flight
	And books an available schedule
	Then the flight booking status is reserved
