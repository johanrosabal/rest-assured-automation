package com.blacksystem.automation.module.meetme.dtos;
import com.blacksystem.automation.module.meetme.enums.StatusCall;
import com.blacksystem.automation.module.meetme.enums.TypeCall;
import com.google.gson.Gson;
import io.restassured.response.Response;

import java.util.Objects;

public class StartCallDto {

    private String clientId;
    private String agentId;
    private String type;
    private String status;

    public StartCallDto(){}

    public StartCallDto fromResponse(Response response){
        StartCallDto dto = new Gson().fromJson(response.getBody().asString(),StartCallDto.class);

        this.clientId = dto.clientId;
        this.agentId = dto.agentId;
        this.type = dto.type;
        this.status =dto.status;

        return this;
    }

    public StartCallDto init(){
        this.clientId = "1";
        this.agentId = "1";
        this.type = TypeCall.PRIVATE.toString();
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartCallDto that = (StartCallDto) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(status, that.status) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, agentId, type);
    }

    @Override
    public String toString() {
        return "StartCallDto{" +
                "clientId='" + clientId + '\'' +
                ", agentId='" + agentId + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String toJson(){
        String clientId="";
        if(this.clientId != null){
            clientId="\"clientId\":\""+this.clientId+"\",";
        }

        String agentId="";
        if(this.agentId != null){
            agentId="\"agentId\":\""+this.agentId+"\",";
        }

        String type="";
        if(this.type != null){
            type="\"type\":\""+this.type+"\",";
        }

        String status="";
        if(this.type != null){
            status="\"status\":\""+this.status+"\",";
        }


        String json = "{"+clientId+agentId+type+status+"}";
        json= json.replace(",}","}");
        return json;
    }
}
