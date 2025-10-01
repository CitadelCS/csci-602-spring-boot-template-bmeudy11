package edu.citadel.api.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
public class CucumberStepDefinitions {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StepData stepData;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private String requestBody;

    // This field holds the result of the latest request
    private ResultActions resultActions;

    private Scenario scenario;

    @Before
    public void setup(Scenario scenario) {
        this.scenario = scenario;
    }

    @When("a GET request is made to the {string} endpoint")
    public void a_get_request_is_made_to_the_endpoint(String endpoint) throws Exception {
        resultActions = mockMvc.perform(get(endpoint));
    }

    @Then("the API should respond with a status code of {int}")
    public void theApiShouldRespondWithAStatusCodeOf(int statusCode) throws Exception {
        try {
            resultActions.andExpect(status().is(statusCode));
            scenario.log("The API responded with a status code of " + statusCode);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Then("the response body should be a JSON object with a status of {string}")
    public void the_response_body_should_be_a_json_object_with_a_status_of(String expectedStatus) throws Exception {
        try{
            resultActions.andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.status").value(expectedStatus));
            scenario.log("The API responded with a json object and status of " + expectedStatus);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Then("the response body should be a JSON object with a version of {string}")
    public void the_response_body_should_be_a_json_object_with_a_version_of(String expectedVersion) throws Exception {
        try{
            resultActions.andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.version").value(expectedVersion));
            scenario.log("The API responded with a json object and version of " + expectedVersion);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Then("the response body should be a JSON object with a user_id of {int}")
    public void the_response_body_should_be_a_json_object_with_a_user_id_of(int expectedUserId) throws Exception {
        try{
            resultActions.andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.user_id").value(expectedUserId));
            scenario.log("The API responded with a json object and user id of " + expectedUserId);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Then("the response body should be valid JSON")
    public void the_response_body_should_be_valid_JSON() throws Exception {
        try {
            String responseBody = resultActions.andReturn().getResponse().getContentAsString();

            System.out.println("ValidJSON?: " + responseBody);
            // readTree will throw a JsonProcessingException if the string is not valid JSON
            objectMapper.readTree(responseBody);
            scenario.log("The API responded with a valid JSON");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Given("a request to create an account with username {string}, password {string}, and email {string}")
    public void aRequestToCreateAnAccountWithUsernamePasswordAndEmail(String username, String password, String email) throws Exception {
        try {
            Map<String, String> body = new HashMap<>();
            body.put("username", username);
            body.put("password", password);
            body.put("email", email);
            requestBody = objectMapper.writeValueAsString(body);
            scenario.log("User creation successful: " + requestBody);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Given("a request to create an account with username {string} and password {string} but no email")
    public void aRequestToCreateAnAccountWithUsernameAndPasswordButNoEmail(String username, String password) throws Exception {
        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);
        requestBody = objectMapper.writeValueAsString(body);
    }

    @When("a user makes a POST request to the {string} endpoint")
    public void aUserMakesAPOSTRequestToTheEndpoint(String endpoint) throws Exception {
        ResultActions action = mockMvc.perform(post(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        // Store the result in a shared context so other step definition classes can access it
        stepData.setLatestAction(action);
    }

    @Then("the account creation API should respond with a status code of {int}")
    public void the_account_creation_API_should_respond_with_a_status_code_of(int statusCode) throws Exception {
        try {
            ResultActions latestAction = stepData.getLatestAction();
            latestAction.andExpect(status().is(statusCode));
            scenario.log("The API responded with a status code of " + statusCode);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @And("the API should NOT respond with a status code of {int}")
    public void the_API_should_NOT_respond_with_a_status_code_of(int unexpectedStatusCode) {
        try {
            resultActions.andExpect(status().is(unexpectedStatusCode));
            fail("Expected a status code other than " + unexpectedStatusCode + ", but that is what the server returned.");
        } catch (AssertionError e) {
            scenario.log("Success: The API correctly did not return the unexpected status code " + unexpectedStatusCode);
        } catch (Exception e) {
            fail("An unexpected error occurred: " + e.getMessage());
        }
    }

    @Then("the response body should NOT be a JSON object with a version of {string}")
    public void the_response_body_should_NOT_be_a_json_object_with_a_version_of(String expectedStatus) throws Exception {
        try {
            resultActions.andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.version").value(expectedStatus));
            fail("Expected a version other than " + expectedStatus + ", but that is what the server returned.");
        } catch (AssertionError e) {
            scenario.log("Success: The API correctly did not return the unexpected version " + expectedStatus);
        } catch (Exception e) {
            fail("An unexpected error occurred: " + e.getMessage());
        }
    }
}