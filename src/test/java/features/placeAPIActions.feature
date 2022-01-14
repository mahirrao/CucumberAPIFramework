#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Add Place API

  @AddPlace
  Scenario Outline: Verify if the place is successgully added with AddPlace API
    Given Add Place Payload with "<name>", "<language>", "<address>"
    When user Calls "AddPlaceAPI" with "POST" http Request
    Then the API call is "success" with Status Code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify placeId created for map with "<name>" using "GetPlaceAPI"
    
Examples:
	| name 						| language 	 | address 							 |
	|	Mayuresh House 	| Marathi-In | 44 Akurdi Lane, Ravet |
#  | Dhairya House   | English    | 1203, Yashone Infiniti|
  
  
  @DeletePlace
  Scenario: Verify if the place is successgully deleted with DeletePlace API
  Given Delete Place Payload
    When user Calls "DeletePlaceAPI" with "DELETE" http Request
    Then the API call is "success" with Status Code 200
    And "status" in response body is "OK"
  