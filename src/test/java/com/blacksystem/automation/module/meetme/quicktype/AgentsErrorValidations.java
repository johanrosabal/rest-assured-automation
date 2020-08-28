package com.blacksystem.automation.module.meetme.quicktype;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

public class AgentsErrorValidations {
    private List<AgentsErrors> errors;

    @JsonProperty("errors")
    public List<AgentsErrors> getErrors() { return errors; }
    @JsonProperty("errors")
    public void setErrors(List<AgentsErrors> value) { this.errors = value; }
}
