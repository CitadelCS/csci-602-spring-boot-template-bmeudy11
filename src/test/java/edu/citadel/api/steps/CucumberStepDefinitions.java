package edu.citadel.api.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class CucumberStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // This field holds the result of the latest request
    private ResultActions resultActions;

    @When("a GET request is made to the {string} endpoint")
    public void a_get_request_is_made_to_the_endpoint(String endpoint) throws Exception {
        resultActions = mockMvc.perform(get(endpoint));
    }

    @Then("the API should respond with a status code of {int}")
    public void theApiShouldRespondWithAStatusCodeOf(int statusCode) throws Exception {
        resultActions.andExpect(status().is(statusCode));
    }

    @Then("the response body should be a JSON object with a status of {string}")
    public void the_response_body_should_be_a_json_object_with_a_status_of(String expectedStatus) throws Exception {
        resultActions.andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(expectedStatus));
    }

    @Then("the response body should be a JSON object with a version of {string}")
    public void the_response_body_should_be_a_json_object_with_a_version_of(String expectedStatus) throws Exception {
        resultActions.andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.version").value(expectedStatus));
    }

    @Then("the response body should be a JSON object with a user_id of {int}")
    public void the_response_body_should_be_a_json_object_with_a_user_id_of(int expectedUserId) throws Exception {
        resultActions.andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.user_id").value(expectedUserId));
    }

    @Then("the response body should be valid JSON")
    public void the_response_body_should_be_valid_JSON() throws Exception {
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();

        System.out.println("ValidJSON?: " + responseBody);
        // readTree will throw a JsonProcessingException if the string is not valid JSON
        objectMapper.readTree(responseBody);
    }
}