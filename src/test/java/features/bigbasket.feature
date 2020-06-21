Feature: Shop on bigbasket.com

Background:

Given Set all browser and other properties
Given open the chrome browser 
And Load the bigbasket url 
And maximize the browser
And apply wait

Scenario: TC001_bigbasket.com to buy rice and toor dal  

Given Mouse over on  Shop by Category   
Given Go to FOODGRAINS, OIL & MASALA and RICE & RICE PRODUCTS 
And Click on BOILED & STEAM RICE
And Get the URL of the page and check with site navigation link
And Choose the Brand as bb Royal
And Go to Ponni Boiled Rice and select 10kg bag from Dropdown
And Click Add button
And click Address 
And Select CHennai as City, Alandur-600016,Chennai as Area  and click Continue
And Go to search box and type Dal
And  Add Toor/Arhar Dal 2kg and set Qty 2 from the list
And Mouse over on My Basket
Then Take a screen shot
Then Click View Basket and Checkout
Then Click the close button 
Then close the browser