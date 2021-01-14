# QA Engineer - Tech Test - Mobile Automation

## Requirements

Pick an app for the operating system for which you're doing your test. Your task is to test the search functionality:
1. Design test scenarios to exercise search flow (don't forget negative tests!)
2. Automate test scenarios using a test framework of your choice
3. Report any bugs you've found

It's up to you how you do this, but we'll want to re-run your UI automation tests to verify the results, and for that we expect you to have chosen a suitable framework.


## Implemented the below scenarios to run on Android emulator
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


## Running the test

- Machine needs Java, appium and maven to run the scripts
- Start appium server on your machine 
- $ cd mobileautomationframework/device-Automation
- $ mvn test -Dos=android -Dsurefire.suiteXmlFiles=cucumberTestng.xml

## Bugs found are
1. Data shown in iOS and Android vary. More details are shown in iOS (Eg. Search Kneesworth House Hospital/Barnet hospital)
2. When searching the data is not diplayed in alphabetical order
3. In Android if we click on the search symbol alone the search bar becomes visible. It doesnt appear when we click on the panel were earch bar appears
4. In case search string is not found there is no text to show if the search was wrong or data not available
5. Inconsistent fields across android and iOS search results
