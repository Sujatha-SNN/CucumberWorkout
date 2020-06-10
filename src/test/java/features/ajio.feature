Feature: Check Women's Clothing in Ajio 

Scenario: Check the flow for selecting AJIO Brand Kurtas for Women

Given Open Ajio.com
And MouseOver on Women,Categories
And Click on Kurtas
And Select Ajio Brand
And Check all the Kurtas are Ajio Brand
And Sort the results based on Discount
And Select Blue Color and add the first kurta to bag
And Verify the error message for skipping size selection
And Select size and add the kurta to bag
And Enter pin-code for estimated delivery
And Enter pincode as 603103 and click Confirm pincode
Then Print the message
Then Click Go to Bag
Then Click on Proceed to Shipping 
#Then Close the browser





