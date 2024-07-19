
@tag
Feature: Purchase the Order from Ecommerce Website

	
	Background:
	Given  I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with username <username> and password <password>
    When I add the product <productName> to Cart 
    And Checkout <productName> and submit the order
    Then  "THANKYOU FOR THE ORDER." message is displayed on ConfirmatoinPage

    Examples: 
      | username                          | password     | productName  |
      | narendarmekala.1997@gmail.com | Naren@1997   | ZARA COAT 3  |
     