package com.blacksystem.automation.application.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Objects;

public class DashboardInformationDto {

    private String projectName;
    private String productOwner;
    private String teamLead;
    private String qaAnalyst;
    private String sprintRelease;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(String productOwner) {
        this.productOwner = productOwner;
    }

    public String getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(String teamLead) {
        this.teamLead = teamLead;
    }

    public String getQaAnalyst() {
        return qaAnalyst;
    }

    public void setQaAnalyst(String qaAnalyst) {
        this.qaAnalyst = qaAnalyst;
    }

    public String getSprintRelease() {
        return sprintRelease;
    }

    public void setSprintRelease(String sprintRelease) {
        this.sprintRelease = sprintRelease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DashboardInformationDto that = (DashboardInformationDto) o;
        return Objects.equals(projectName, that.projectName) &&
                Objects.equals(productOwner, that.productOwner) &&
                Objects.equals(teamLead, that.teamLead) &&
                Objects.equals(qaAnalyst, that.qaAnalyst) &&
                Objects.equals(sprintRelease, that.sprintRelease);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, productOwner, teamLead, qaAnalyst, sprintRelease);
    }

    @Override
    public String toString() {
        return "DashboardInformationDto{" +
                "projectName='" + projectName + '\'' +
                ", productOwner='" + productOwner + '\'' +
                ", teamLead='" + teamLead + '\'' +
                ", qaAnalyst='" + qaAnalyst + '\'' +
                ", sprintRelease='" + sprintRelease + '\'' +
                '}';
    }

    /*
    * Convert Object DTO to a MAP Object
    * */
    public Map toMap(){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(this, Map.class);
    }
}
