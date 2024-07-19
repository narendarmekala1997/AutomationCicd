
@tag
Feature: Error Validation



  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
   	 When Logged in with username <username> and password <password>
    Then  "Incorrect email or password." message is displayed

     Examples: 
      | username                          | password      |
      | narendarmekala.1997@gmail.com     | Naren@199    |