

@tag
Feature: Purchase the order from Ecommerce Website.

Background:
Given i landed on Ecommerce Page

  @Smoke
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with <username> and  <password>
    When i add the product <productName> to cart
    And Checkout <productName> and sumbit the order
    Then verify the success message as "THANKYOU FOR THE ORDER." displayed on confirmation Page. 

    Examples: 
      | username                | password      | productName |
      | johndeepak444@gmail.com |     Deepak@95 | ZARA COAT 3 |
      
