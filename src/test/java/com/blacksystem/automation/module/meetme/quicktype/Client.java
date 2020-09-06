package com.blacksystem.automation.module.meetme.quicktype;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {
    private String id;
    private String firstName;
    private String lastName;
    private String secondLastName;
    private String birthDate;
    private String gender;
    private String nickName;
    private String email;
    private long coins;
    private String phoneToken;
    private String language;
    private String country;
    private boolean emailVerified;
    private boolean active;
    private String dateCreated;
    private String dateModified;

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("firstName")
    public String getFirstName() { return firstName; }
    @JsonProperty("firstName")
    public void setFirstName(String value) { this.firstName = value; }

    @JsonProperty("lastName")
    public String getLastName() { return lastName; }
    @JsonProperty("lastName")
    public void setLastName(String value) { this.lastName = value; }

    @JsonProperty("secondLastName")
    public String getSecondLastName() { return secondLastName; }
    @JsonProperty("secondLastName")
    public void setSecondLastName(String value) { this.secondLastName = value; }

    @JsonProperty("birthDate")
    public String getBirthDate() { return birthDate; }
    @JsonProperty("birthDate")
    public void setBirthDate(String value) { this.birthDate = value; }

    @JsonProperty("gender")
    public String getGender() { return gender; }
    @JsonProperty("gender")
    public void setGender(String value) { this.gender = value; }

    @JsonProperty("nickName")
    public String getNickName() { return nickName; }
    @JsonProperty("nickName")
    public void setNickName(String value) { this.nickName = value; }

    @JsonProperty("email")
    public String getEmail() { return email; }
    @JsonProperty("email")
    public void setEmail(String value) { this.email = value; }

    @JsonProperty("coins")
    public long getCoins() { return coins; }
    @JsonProperty("coins")
    public void setCoins(long value) { this.coins = value; }

    @JsonProperty("phoneToken")
    public String getPhoneToken() { return phoneToken; }
    @JsonProperty("phoneToken")
    public void setPhoneToken(String value) { this.phoneToken = value; }

    @JsonProperty("language")
    public String getLanguage() { return language; }
    @JsonProperty("language")
    public void setLanguage(String value) { this.language = value; }

    @JsonProperty("country")
    public String getCountry() { return country; }
    @JsonProperty("country")
    public void setCountry(String value) { this.country = value; }

    @JsonProperty("emailVerified")
    public boolean getEmailVerified() { return emailVerified; }
    @JsonProperty("emailVerified")
    public void setEmailVerified(boolean value) { this.emailVerified = value; }

    @JsonProperty("active")
    public boolean getActive() { return active; }
    @JsonProperty("active")
    public void setActive(boolean value) { this.active = value; }

    @JsonProperty("dateCreated")
    public String getDateCreated() { return dateCreated; }
    @JsonProperty("dateCreated")
    public void setDateCreated(String value) { this.dateCreated = value; }

    @JsonProperty("dateModified")
    public String getDateModified() { return dateModified; }
    @JsonProperty("dateModified")
    public void setDateModified(String value) { this.dateModified = value; }
}
