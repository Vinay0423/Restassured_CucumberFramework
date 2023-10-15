Feature: Users

Scenario: Getting users details

Given setting headers
When user send the get request using "getUsersApi"
Then statuscode of response is 200
And getting the response body

