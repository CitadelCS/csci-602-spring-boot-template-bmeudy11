Feature: Info Endpoint
  As a user of the API
  I want to be able to check the version of the endpoint
  So that I know the version is as expected

  Scenario: Check the info of the API
    When a GET request is made to the "/info" endpoint
    Then the API should respond with a status code of 200
    And the response body should be valid JSON
    And the response body should be a JSON object with a version of "1.0.0"

  Scenario: Requesting info with incorrect expectation (Expected Failure)
    When a GET request is made to the "/info" endpoint
    # expected failure
    Then the response body should be a JSON object with a version of "5.0.0"