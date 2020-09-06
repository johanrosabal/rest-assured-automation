package com.blacksystem.automation.module.meetme.dtos;

import com.google.gson.Gson;
import io.restassured.response.Response;

import java.util.Objects;

public class EndCallDto {

    private String agentId;
    private String clientId;
    private String clientName;
    private String agentName;
    private String sessionId;

    private String type;
    private String startDate;
    private int durationInSeconds;
    private String endDate;
    private double totalAgent;
    private double totalService;
    private String dateModified;
    private int totalInSeconds;
    private double totalCall;
    private String duration;
    private double ratePerMinute;
    private double ratePerSecond;
    private int durationInMilliseconds;
    private int totalCoins;
    private double totalInMinutes;
    private double agentRate;
    private String status;

    public EndCallDto(){}

    public EndCallDto fromResponse(Response response){
        EndCallDto dto = new Gson().fromJson(response.getBody().asString(),EndCallDto.class);

        this.agentId = dto.agentId;
        this.clientId = dto.clientId;
        this.clientName = dto.clientName;
        this.agentName = dto.agentName;
        this.sessionId = dto.sessionId;
        this.type = dto.type;
        this.startDate = dto.startDate;
        this.durationInSeconds = dto.durationInSeconds;
        this.endDate = dto.endDate;
        this.totalAgent = dto.totalAgent;
        this.totalService = dto.totalService;
        this.dateModified = dto.dateModified;
        this.totalInSeconds = dto.totalInSeconds;
        this.totalCall = dto.totalCall;
        this.duration = dto.duration;
        this.ratePerMinute = dto.ratePerMinute;
        this.ratePerSecond = dto.ratePerSecond;
        this.durationInMilliseconds = dto.durationInMilliseconds;
        this.totalCoins = dto.totalCoins;
        this.totalInMinutes = dto.totalInMinutes;
        this.agentRate = dto.agentRate;
        this.status = dto.status;

        return this;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getTotalAgent() {
        return totalAgent;
    }

    public void setTotalAgent(double totalAgent) {
        this.totalAgent = totalAgent;
    }

    public double getTotalService() {
        return totalService;
    }

    public void setTotalService(double totalService) {
        this.totalService = totalService;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public int getTotalInSeconds() {
        return totalInSeconds;
    }

    public void setTotalInSeconds(int totalInSeconds) {
        this.totalInSeconds = totalInSeconds;
    }

    public double getTotalCall() {
        return totalCall;
    }

    public void setTotalCall(double totalCall) {
        this.totalCall = totalCall;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getRatePerMinute() {
        return ratePerMinute;
    }

    public void setRatePerMinute(double ratePerMinute) {
        this.ratePerMinute = ratePerMinute;
    }

    public double getRatePerSecond() {
        return ratePerSecond;
    }

    public void setRatePerSecond(double ratePerSecond) {
        this.ratePerSecond = ratePerSecond;
    }

    public int getDurationInMilliseconds() {
        return durationInMilliseconds;
    }

    public void setDurationInMilliseconds(int durationInMilliseconds) {
        this.durationInMilliseconds = durationInMilliseconds;
    }

    public int getTotalCoins() {
        return totalCoins;
    }

    public void setTotalCoins(int totalCoins) {
        this.totalCoins = totalCoins;
    }

    public double getTotalInMinutes() {
        return totalInMinutes;
    }

    public void setTotalInMinutes(double totalInMinutes) {
        this.totalInMinutes = totalInMinutes;
    }

    public double getAgentRate() {
        return agentRate;
    }

    public void setAgentRate(double agentRate) {
        this.agentRate = agentRate;
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
        EndCallDto that = (EndCallDto) o;
        return durationInSeconds == that.durationInSeconds &&
                totalAgent == that.totalAgent &&
                totalService == that.totalService &&
                totalInSeconds == that.totalInSeconds &&
                totalCall == that.totalCall &&
                ratePerMinute == that.ratePerMinute &&
                ratePerSecond == that.ratePerSecond &&
                durationInMilliseconds == that.durationInMilliseconds &&
                totalCoins == that.totalCoins &&
                totalInMinutes == that.totalInMinutes &&
                agentRate == that.agentRate &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(clientName, that.clientName) &&
                Objects.equals(agentName, that.agentName) &&
                Objects.equals(sessionId, that.sessionId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(dateModified, that.dateModified) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agentId, clientId, clientName, agentName, sessionId, type, startDate, durationInSeconds, endDate, totalAgent, totalService, dateModified, totalInSeconds, totalCall, duration, ratePerMinute, ratePerSecond, durationInMilliseconds, totalCoins, totalInMinutes, agentRate, status);
    }

    @Override
    public String toString() {
        return "EndCallDto{" +
                "agentId='" + agentId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", clientName='" + clientName + '\'' +
                ", agentName='" + agentName + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", type='" + type + '\'' +
                ", startDate='" + startDate + '\'' +
                ", durationInSeconds=" + durationInSeconds +
                ", endDate='" + endDate + '\'' +
                ", totalAgent=" + totalAgent +
                ", totalService=" + totalService +
                ", dateModified='" + dateModified + '\'' +
                ", totalInSeconds=" + totalInSeconds +
                ", totalCall=" + totalCall +
                ", duration='" + duration + '\'' +
                ", ratePerMinute=" + ratePerMinute +
                ", ratePerSecond=" + ratePerSecond +
                ", durationInMilliseconds=" + durationInMilliseconds +
                ", totalCoins=" + totalCoins +
                ", totalInMinutes=" + totalInMinutes +
                ", agentRate=" + agentRate +
                ", status='" + status + '\'' +
                '}';
    }
}
