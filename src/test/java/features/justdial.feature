Feature: Browse JustDial.com for services

Scenario Outline: Book air tickets on JustDial.com

Given Cick on Air Tickets
And Type Chennai and choose <FromAirPort> as Leaving From 
And Type Toronto and select <ToAirPort> as Going To
And Set Departure as 2020, July 22
And Add Adult 2 , Children 1 click and Search
And Select Air Canada from multi-airline itineraries
And Click on Price to sort the result
And Click on +Details of first result under Price
And Capture the Flight Arrival times.
And Capture the total price in a list and Click on Book
And Capture the Airport name base on the list of time
Then Captur the total fare 
Then Print the difference amount from previous total price

Examples:

|FromAirPort|ToAirPort|
|Chennai, IN - Chennai Airport (MAA)|New Delhi, IN - Indira Gandhi Airport (DEL)|
