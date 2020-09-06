package com.blacksystem.automation.module.meetme.quicktype;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

public class ResponseMessages {
    private List<AgentsErrors> errors;

    @JsonProperty("errors")
    public List<AgentsErrors> getErrors() { return errors; }
    @JsonProperty("errors")
    public void setErrors(List<AgentsErrors> value) { this.errors = value; }

    private Client[] clients;

    @JsonProperty("clients")
    public Client[] getClients() { return clients; }
    @JsonProperty("clients")
    public void setClients(Client[] value) { this.clients = value; }

    private Agent[] agents;

    @JsonProperty("agents")
    public Agent[] getAgents() { return agents; }
    @JsonProperty("agents")
    public void setAgents(Agent[] value) { this.agents = value; }
}
