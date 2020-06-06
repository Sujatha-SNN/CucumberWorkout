Feature: CarWale - Used Cars

Scenario: TC001_Validate the cars are listed with KMs Low to High

Given Go to https://www.carwale.com/ 
Given Click on Used 
Given Select the City as Chennai
Given Select budget min (8L) and max (12L) and Click Search
Given Select Cars with Photos under Only Show Cars With 
Given Select Manufacturer as "Hyundai" --> Creta
Given Select Fuel Type as Petrol
Given Select Best Match as "KM: Low to High"
Given Validate the Cars are listed with KMs Low to High
Given Add the least KM ran car to Wishlist
Given Go to Wishlist and Click on More Details
Then Print the first car name
Then Print all the details under Overview in the Same way as displayed in application 
Then Close the browser