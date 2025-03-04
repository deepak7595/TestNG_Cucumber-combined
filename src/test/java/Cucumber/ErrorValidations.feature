
@tag
Feature: Error Validations
 
  @Regression
  Scenario Outline: Title of your scenario outline
    Given i landed on Ecommerce Page
    When Logged in with <username> and  <password>
    Then Verify the Error message "Incorrect email or password."

    Examples: 
      | username                | password       | 
      | johndeepak444@gmail.com |     Deepak@9   | 
