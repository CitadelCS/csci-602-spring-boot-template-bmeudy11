Feature: Healthcheck Endpoint
  As a user of the API
  I want to be able to check its health status
  So that I know the service is operational

  Scenario: Check the health status of the API
    When a GET request is made to the "/health" endpoint
    Then the API should respond with a status code of 200
    And the response body should be valid JSON
    And the response body should be a JSON object with a status of "ok"

  Scenario: Requesting health status with incorrect expectation (Expected Failure)
    When a GET request is made to the "/health" endpoint
    # expected failure
    Then the API should NOT respond with a status code of 404