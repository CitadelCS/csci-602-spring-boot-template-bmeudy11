Feature: Account Endpoint
  As a user of the API
  I want to be able to get details for an account with a specified id
  So that I know the details of that account

  Scenario:
    When a GET request is made to the "http://localhost:5001/account/1" endpoint
    Then the API should respond with a status code of 200
    And the response body should be a JSON object with a user_id of 1

  Scenario: Requesting account information with incorrect expectation (Expected Failure)
    When a GET request is made to the "http://localhost:5001/account/59999" endpoint
    # expected failure
    Then the response body should be a JSON object with a user_id of 59999