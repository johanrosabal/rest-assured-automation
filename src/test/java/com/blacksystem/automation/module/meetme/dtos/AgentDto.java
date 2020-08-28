package com.blacksystem.automation.module.meetme.dtos;

import com.blacksystem.automation.application.utils.DatesUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.response.Response;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class AgentDto {

    private String id;
    private String firstName ;
    private String lastName ;
    private String secondLastName ;
    private String ssn ;
    private String birthDate ;
    private String gender ;
    private String nickName ;
    private String email ;

    private String phoneNumber ;
    private String language ;
    private String country ;

    private String bankAccount ;
    private String bankName ;

    private String description ;
    private String swipperPhoto ;
    private String heroPhoto ;

    private int coins;
    private int totalCalls;
    private int rate;
    private Boolean emailVerified ;
    private String notes;
    private boolean active ;
    private boolean status ;
    private String phoneToken ;

    private String dateModified;
    private String dateCreated;

    public AgentDto(){

    }

    public AgentDto fromResponse(Response response){
        AgentDto dto = new Gson().fromJson(response.getBody().asString(),AgentDto.class);

        this.id = dto.id;
        this.firstName = dto.firstName;
        this.lastName = dto.lastName;
        this.secondLastName = dto.secondLastName;
        this.ssn = dto.ssn;
        this.birthDate = dto.birthDate;
        this.gender = dto.gender;
        this.nickName = dto.nickName;
        this.email = dto.email;
        this.phoneNumber = dto.phoneNumber;

        this.language = dto.language;
        this.country = dto.country;
        this.phoneToken = dto.phoneToken;

        this.bankAccount = dto.bankAccount;
        this.bankName = dto.bankName;

        this.description = dto.description;
        this.swipperPhoto = dto.swipperPhoto;
        this.heroPhoto = dto.heroPhoto;
        this.emailVerified = dto.emailVerified;

        this.notes = dto.notes;
        this.rate = dto.rate;
        this.coins = dto.coins;
        this.totalCalls = dto.totalCalls;

        this.active = dto.active;
        this.status = dto.status;

        this.dateModified = dto.dateModified;
        this.dateCreated = dto.dateCreated;

        return this;
    }

    public AgentDto init(){
        Faker faker = new Faker();

        this.id = UUID.randomUUID().toString();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.secondLastName = faker.name().lastName();
        this.ssn = "22240943802";
        this.birthDate = "01-01-2001";
        this.gender = "M";
        this.nickName = faker.funnyName().name();
        this.email = "test@test.com";
        this.phoneNumber = "2222222222";

        this.language = "ES";
        this.country = "CR";
        this.description = "Some Description";

        /*******************************************************************************************************************/

        this.swipperPhoto = "";
        this.heroPhoto = "";

        this.bankAccount = "";
        this.bankName = "";

        this.phoneToken = "";

        this.emailVerified = false;

        this.active = true;
        this.status = false;

        this.notes = "";
        this.rate = 0;
        this.coins = 0;
        this.totalCalls = 0;

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

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSwipperPhoto() {
        return swipperPhoto;
    }

    public void setSwipperPhoto(String swipperPhoto) {
        this.swipperPhoto = swipperPhoto;
    }

    public String getHeroPhoto() {
        return heroPhoto;
    }

    public void setHeroPhoto(String heroPhoto) {
        this.heroPhoto = heroPhoto;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getTotalCalls() {
        return totalCalls;
    }

    public void setTotalCalls(int totalCalls) {
        this.totalCalls = totalCalls;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPhoneToken() {
        return phoneToken;
    }

    public void setPhoneToken(String phoneToken) {
        this.phoneToken = phoneToken;
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
        AgentDto agentDto = (AgentDto) o;
        return coins == agentDto.coins &&
                totalCalls == agentDto.totalCalls &&
                rate == agentDto.rate &&
                active == agentDto.active &&
                status == agentDto.status &&
                Objects.equals(id, agentDto.id) &&
                Objects.equals(firstName, agentDto.firstName) &&
                Objects.equals(lastName, agentDto.lastName) &&
                Objects.equals(secondLastName, agentDto.secondLastName) &&
                Objects.equals(ssn, agentDto.ssn) &&
                Objects.equals(birthDate, agentDto.birthDate) &&
                Objects.equals(gender, agentDto.gender) &&
                Objects.equals(nickName, agentDto.nickName) &&
                Objects.equals(email, agentDto.email) &&
                Objects.equals(phoneNumber, agentDto.phoneNumber) &&
                Objects.equals(language, agentDto.language) &&
                Objects.equals(country, agentDto.country) &&
                Objects.equals(bankAccount, agentDto.bankAccount) &&
                Objects.equals(bankName, agentDto.bankName) &&
                Objects.equals(description, agentDto.description) &&
                Objects.equals(swipperPhoto, agentDto.swipperPhoto) &&
                Objects.equals(heroPhoto, agentDto.heroPhoto) &&
                Objects.equals(emailVerified, agentDto.emailVerified) &&
                Objects.equals(notes, agentDto.notes) &&
                Objects.equals(phoneToken, agentDto.phoneToken) &&
                Objects.equals(dateModified, agentDto.dateModified) &&
                Objects.equals(dateCreated, agentDto.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, secondLastName, ssn, birthDate, gender, nickName, email, phoneNumber, language, country, bankAccount, bankName, description, swipperPhoto, heroPhoto, coins, totalCalls, rate, emailVerified, notes, active, status, phoneToken, dateModified, dateCreated);
    }

    @Override
    public String toString() {
        return "AgentDto{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", ssn='" + ssn + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", bankName='" + bankName + '\'' +
                ", description='" + description + '\'' +
                ", swipperPhoto='" + swipperPhoto + '\'' +
                ", heroPhoto='" + heroPhoto + '\'' +
                ", coins=" + coins +
                ", totalCalls=" + totalCalls +
                ", rate=" + rate +
                ", emailVerified=" + emailVerified +
                ", notes='" + notes + '\'' +
                ", active=" + active +
                ", status=" + status +
                ", phoneToken='" + phoneToken + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
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
