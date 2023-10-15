Feature: Po type

Scenario: User can send get request for po type Api

Given user  should get "data.user.token" token from "loginApi" set token to header
When user should sents "Get" request for "poTypeApi" 
Then the statuscode is 200
And get all types

