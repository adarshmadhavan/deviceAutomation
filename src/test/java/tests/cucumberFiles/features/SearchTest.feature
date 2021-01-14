
	
@login
Feature: Verify search functionality on sensyne app

  Background: Create an instance of android or ios driver before each scenario
    Given User has sensyne "android" app

  Scenario: User should be able to search on app for hospital
    Given user lands on "search" screen
    When user has "valid" search string
    And user enters hospital name
    And taps on "dropdown" list
    Then "Data" elements should be visible
 		
  Scenario: User should be able to navigate back
    Given user lands on "search" screen
    When user has "valid" search string
    And user enters hospital name
    And taps on "dropdown" list
    Then "Data" elements should be visible
    When taps on "backButton" list
    Then user lands on "backToSearch" screen
    
  Scenario: User should be displayed meaningful error message
    Given user lands on "search" screen
    When user has "inValid" search string
    And user enters hospital name
    Then "dropdown" elements should be visible