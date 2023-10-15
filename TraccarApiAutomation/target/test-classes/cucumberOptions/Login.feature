Feature: Login Api

Scenario: User Sents Requests for LoginApi

Given user sets header
When user sending "loginApi" with "Post" request
Then status code in responsebody  is 200
And "status.success" in response body is "true"
And "status.message" in response body is "success"
