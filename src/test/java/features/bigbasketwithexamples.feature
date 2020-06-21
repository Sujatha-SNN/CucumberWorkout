Feature: Shop on bigbasket.com

Background:

Given Set all browser and other properties
Given open the chrome browser 
And Load the bigbasket url 
And maximize the browser
And apply wait

Scenario Outline: TC001_bigbasket.com to buy juices  

Given Mouse over Shop by Category   
Given Go to Beverages and Fruit juices & Drinks 
And Click on Juices
And Click <juice1> and <juice2> under Brand
And Check count of the products from each Brands and total count
And Check whether the products is availabe with Add button
And Add the First listed available product
And click Address 
And Select <city> as City, <area> as Area  and click Continue
And Mouse over on My Basket print the product name. count and price.
Then Click View Basket and Checkout
Then Click the close button and close the browser

Examples:

|juice1 | juice2 | city | area |
|Tropicana|Real|Chennai|Alandur|
|Tropicana|Amul|Chennai|T.Nagar|

