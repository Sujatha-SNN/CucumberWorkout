Feature: Browse BikeWale.com for bikes comparison

Scenario Outline: TC001_BikeWale.com for comparing bikes ratings

Given Go to https://www.bikewale.com/
Given Go to menu and click new bikes
Given Click New Bikes Then compare bikes
Given Add first bike as <bikeone> and model as <modelone>
And Add second bike <biketwo> model as <modeltwo> and version Dual Channel ABS - BS VI
And  Add bike brand <bikethree> model as <modelthree>
And  click compare
Then Find and print the maximum overall rating of all the bikes 
Then Print the max rating

Examples:

|bikeone|modelone|biketwo|modeltwo|bikethree|modelthree|
|Jawa|42|Royal Enfield|Thunderbird 350|Kawasaki|Ninja 300|
|Jawa|Standard|Royal Enfield|Thunderbird 350X|Kawasaki|Ninja 300|