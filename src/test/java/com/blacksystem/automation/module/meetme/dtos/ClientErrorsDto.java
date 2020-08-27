package com.blacksystem.automation.module.meetme.dtos;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class ClientErrorsDto {
    private String id;
    private String firstName;
    private String lastName;
    private String secondLastName;
    private String birthDate;
    private String gender;
    private String nickName;
    private String email;
    private String language;
    private String country;

    public ClientErrorsDto(){

    }

    public ClientErrorsDto(Response response){
        List<Map<String, String>> errors = response.getBody().jsonPath().getList("errors");

        this.id             = errors.get(0).get("id");
        this.firstName      = errors.get(1).get("firstName");
        this.lastName       = errors.get(2).get("lastName");
        this.secondLastName = errors.get(3).get("secondLastName");
        this.birthDate      = errors.get(4).get("birthDate");
        this.gender         = errors.get(5).get("gender");
        this.nickName       = errors.get(6).get("nickName");
        this.email          = errors.get(7).get("email");
        this.language       = errors.get(8).get("language");
        this.country        = errors.get(9).get("country");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
