Feature: Base currency api

Scenario: User can perform get request using basecurrent api

Given user should get "data.user.token" token from "loginApi" set token to header
When user sents "Get" request for "baseCurrenyApi" 
Then statuscode is 200

