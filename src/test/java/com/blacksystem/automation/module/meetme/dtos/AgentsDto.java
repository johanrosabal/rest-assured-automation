package com.blacksystem.automation.module.meetme.dtos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AgentsDto {
    private List<AgentDto> Agents;

    public AgentsDto(Response response) {
        String json = response.getBody().asString();
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.Agents = mapper.readValue(json,new TypeReference<List<AgentDto>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<AgentDto> getAgents() {
        return Agents;
    }

    public void setAgents(List<AgentDto> Agents) {
        this.Agents = Agents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgentsDto that = (AgentsDto) o;
        return Objects.equals(Agents, that.Agents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Agents);
    }

    @Override
    public String toString() {
        return "AgentsDto{" +
                "Agents=" + Agents +
                '}';
    }

    public Map toMap(){
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.convertValue(this,Map.class);
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
