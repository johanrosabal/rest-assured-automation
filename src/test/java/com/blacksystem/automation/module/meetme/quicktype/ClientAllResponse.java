package com.blacksystem.automation.module.meetme.quicktype;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientAllResponse {

    private Client[] clients;

    @JsonProperty("clients")
    public Client[] getClients() { return clients; }
    @JsonProperty("clients")
    public void setClients(Client[] value) { this.clients = value; }
}
