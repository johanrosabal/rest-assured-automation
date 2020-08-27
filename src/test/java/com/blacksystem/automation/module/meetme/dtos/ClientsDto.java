package com.blacksystem.automation.module.meetme.dtos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ClientsDto {
    private List<ClientDto> clients;

    public ClientsDto(Response response) {
        String json = response.getBody().asString();
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.clients = mapper.readValue(json,new TypeReference<List<ClientDto>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ClientDto> getClients() {
        return clients;
    }

    public void setClients(List<ClientDto> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientsDto that = (ClientsDto) o;
        return Objects.equals(clients, that.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clients);
    }

    @Override
    public String toString() {
        return "ClientsDto{" +
                "clients=" + clients +
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
