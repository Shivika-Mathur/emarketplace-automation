#Author: mathurshivika@gmail.com


Feature: Login Funtionality
			In order to do online shopping
			as a valid online customer
			i want to login succesfully into website
			add items to the cart
			proceed to checkout

  
  Scenario: Login Successful
    Given I am on the login page of the application
    When I enter valid credentials
    Then I should be taken to the website homepage
    Then I am able to select the items
    Then close the browser
