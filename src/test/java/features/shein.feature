Feature: Shop on Shein.com under Women category

Background:

Given Set the browser and other properties
Given Open chrome browser 
Given Load "https://www.shein.com/"
Given Maximize the brower  
Given Apply ImplicitWait 

Scenario: TC001_shein.com for black jeans 


And Mouseover on Clothing and click Jeans
And Choose Black under Jeans product count
And Check size as medium
And Click + in color
And Check whether the color is black
And Click first item to Add to Bag 
And Click the size as M abd click Submit
And Click view Bag 
And Check the size is Medium or not.
Then Close the browser