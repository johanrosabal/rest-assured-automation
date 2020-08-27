package com.blacksystem.automation.module.meetme.dtos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ResponseDto {

    private String id;
    private String status;
    private String message;

    public ResponseDto(Response response) {
//        List<Map<String, String>> errors = response.getBody().jsonPath().getList("errors");
//        Map<String, String> map = errors.get(0);

        ResponseDto dto = new Gson().fromJson(response.getBody().asString(),ResponseDto.class);
        this.id = dto.id;
        this.status = dto.status;
        this.message = dto.message;
    }

    public ResponseDto(String id, String status, String message) {
        this.id = id;
        this.status = status;
        this.message = message;
    }

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

}
