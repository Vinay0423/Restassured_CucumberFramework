Feature: current api functionality

  Scenario: Sending get request to current api
    Given get "data.user.token" token from "loginApi" set token to header
    When sending "currentApi" with "Get" request
    Then statusCode is 200
    And "data.user.id" value  is "80"
    And "qa"  the value for "data.user.firstName"
