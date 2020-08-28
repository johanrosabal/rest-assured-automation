package com.blacksystem.automation.module.meetme.dtos;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class AgentErrorsDto {

    private String id;
    private String firstName;
    private String lastName;
    private String secondLastName;
    private String ssn;
    private String birthDate;
    private String gender;
    private String nickName;
    private String email;
    private String phoneNumber;
    private String language;
    private String country;
//    private String phoneToken;
//    private String bankAccount;
//    private String bankName;
//    private String description;
//    private String swipperPhoto;
//    private String heroPhoto;

    public AgentErrorsDto(){

    }

    public AgentErrorsDto(Response response){
        List<Map<String, String>> errors = response.getBody().jsonPath().getList("errors");

        this.id             = errors.get(0).get("id");
        this.firstName      = errors.get(1).get("firstName");
        this.lastName       = errors.get(2).get("lastName");
        this.secondLastName = errors.get(3).get("secondLastName");
        this.ssn            = errors.get(4).get("ssn");
        this.birthDate      = errors.get(5).get("birthDate");
        this.gender         = errors.get(6).get("gender");
        this.nickName       = errors.get(7).get("nickName");
        this.email          = errors.get(8).get("email");
        this.phoneNumber    = errors.get(9).get("phoneNumber");
        this.language       = errors.get(10).get("language");
        this.country        = errors.get(11).get("country");
//        this.phoneToken     = errors.get(12).get("phoneToken");
//        this.bankAccount    = errors.get(13).get("bankAccount");
//        this.bankName       = errors.get(14).get("bankName");
//        this.description    = errors.get(15).get("description");
//        this.swipperPhoto   = errors.get(16).get("swipperPhoto");
//        this.heroPhoto      = errors.get(17).get("heroPhoto");

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

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

//    public String getPhoneToken() {
//        return phoneToken;
//    }
//
//    public void setPhoneToken(String phoneToken) {
//        this.phoneToken = phoneToken;
//    }
//
//    public String getBankAccount() {
//        return bankAccount;
//    }
//
//    public void setBankAccount(String bankAccount) {
//        this.bankAccount = bankAccount;
//    }
//
//    public String getBankName() {
//        return bankName;
//    }
//
//    public void setBankName(String bankName) {
//        this.bankName = bankName;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getSwipperPhoto() {
//        return swipperPhoto;
//    }
//
//    public void setSwipperPhoto(String swipperPhoto) {
//        this.swipperPhoto = swipperPhoto;
//    }
//
//    public String getHeroPhoto() {
//        return heroPhoto;
//    }
//
//    public void setHeroPhoto(String heroPhoto) {
//        this.heroPhoto = heroPhoto;
//    }
}
