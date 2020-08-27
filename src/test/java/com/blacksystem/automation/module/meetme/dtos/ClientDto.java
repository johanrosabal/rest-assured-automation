package com.blacksystem.automation.module.meetme.dtos;

import com.blacksystem.automation.application.utils.DatesUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.response.Response;

import java.awt.image.RescaleOp;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ClientDto {

    private String id;
    private String firstName;
    private String lastName;
    private String secondLastName;
    private String birthDate;
    private String gender;
    private String nickName;
    private String email;

    private String phoneToken;
    private String language;
    private String country;

    private int coins;
    private Boolean emailVerified;
    private boolean active;

    private String dateCreated;
    private String dateModified;

    public ClientDto(){

    }

    public ClientDto fromResponse(Response response){
        ClientDto dto = new Gson().fromJson(response.getBody().asString(),ClientDto.class);

        this.id = dto.id;
        this.firstName = dto.firstName;
        this.lastName = dto.lastName;
        this.secondLastName = dto.secondLastName;
        this.birthDate = dto.birthDate;
        this.gender = dto.gender;
        this.nickName = dto.nickName;
        this.email = dto.email;

        this.language = dto.language;
        this.country = dto.country;
        this.phoneToken = dto.phoneToken;

        this.coins = dto.coins;
        this.emailVerified = dto.emailVerified;
        this.active = dto.active;

        this.dateModified = dto.dateModified;
        this.dateCreated = dto.dateCreated;

        return this;
    }

    public ClientDto init(){
        Faker faker = new Faker();

        this.id = UUID.randomUUID().toString();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.secondLastName = faker.name().lastName();
        this.birthDate = "01-01-2001";
        this.gender = "M";
        this.nickName = faker.funnyName().name();
        this.email = "test@test.com";

        this.language = "ES";
        this.country = "CR";
        this.phoneToken = "99999-55555-555-99999";

        this.coins = 0;
        this.emailVerified = false;
        this.active = true;

        this.dateModified = DatesUtils.convertDateToString(new Date(), DatesUtils.US_DATE);
        this.dateCreated =  DatesUtils.convertDateToString(new Date(), DatesUtils.US_DATE);

        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdUUID() {
        this.id = UUID.randomUUID().toString();
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

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getPhoneToken() {
        return phoneToken;
    }

    public void setPhoneToken(String phoneToken) {
        this.phoneToken = phoneToken;
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

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto clientDto = (ClientDto) o;
        return coins == clientDto.coins &&
                active == clientDto.active &&
                Objects.equals(id, clientDto.id) &&
                Objects.equals(firstName, clientDto.firstName) &&
                Objects.equals(lastName, clientDto.lastName) &&
                Objects.equals(secondLastName, clientDto.secondLastName) &&
                Objects.equals(birthDate, clientDto.birthDate) &&
                Objects.equals(gender, clientDto.gender) &&
                Objects.equals(nickName, clientDto.nickName) &&
                Objects.equals(email, clientDto.email) &&
                Objects.equals(phoneToken, clientDto.phoneToken) &&
                Objects.equals(language, clientDto.language) &&
                Objects.equals(country, clientDto.country) &&
                Objects.equals(emailVerified, clientDto.emailVerified) &&
                Objects.equals(dateCreated, clientDto.dateCreated) &&
                Objects.equals(dateModified, clientDto.dateModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, secondLastName, birthDate, gender, nickName, email, phoneToken, language, country, coins, emailVerified, active, dateCreated, dateModified);
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", phoneToken='" + phoneToken + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", coins=" + coins +
                ", emailVerified=" + emailVerified +
                ", active=" + active +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateModified='" + dateModified + '\'' +
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
