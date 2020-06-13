Feature: Shop on ShopClues.com under Women category

Background:

Given Set browser and other properties
Given Open the chrome browser 
Given Load https://www.shopclues.com/ 
Given Maximize brower  
Given Apply wait

Scenario: TC001_ShopClues.com for shoes under Women Casual Wear shoes

And Mouseover on women and click Casual Shoes
And Select Color as Black
And Check whether the product name contains the word black
And If so, add the product name and price in to Map
And Check Display the count of shoes which are more than 500 rupees
And Click the highest price of the 'shoe
And Get the current page URL and check with the product ID
And Copy the offercode 
And Select size, color and click ADD TO CART
And Mouse over on Shopping cart and click View Cart 
And Type Pincode as 600016 click Submit and Place Order
Then Close the Browser