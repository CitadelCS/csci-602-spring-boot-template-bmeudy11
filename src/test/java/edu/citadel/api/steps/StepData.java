package edu.citadel.api.steps;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

@Component
@ScenarioScope
public class StepData {
    private ResultActions latestAction;

    public ResultActions getLatestAction() {
        return latestAction;
    }

    public void setLatestAction(ResultActions latestAction) {
        this.latestAction = latestAction;
    }
}