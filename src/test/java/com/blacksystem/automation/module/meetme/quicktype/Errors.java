package com.blacksystem.automation.module.meetme.quicktype;

import com.fasterxml.jackson.annotation.*;

public class Errors {

    /**********************************************  [ Agents ] *************************************************************/
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
    private String phoneToken;
    private String bankAccount;
    private String bankName;
    private String description;
    private String swipperPhoto;
    private String heroPhoto;

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

    @JsonProperty("ssn")
    public String getSsn() { return ssn; }
    @JsonProperty("ssn")
    public void setSsn(String value) { this.ssn = value; }

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

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() { return phoneNumber; }
    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String value) { this.phoneNumber = value; }

    @JsonProperty("language")
    public String getLanguage() { return language; }
    @JsonProperty("language")
    public void setLanguage(String value) { this.language = value; }

    @JsonProperty("country")
    public String getCountry() { return country; }
    @JsonProperty("country")
    public void setCountry(String value) { this.country = value; }

    @JsonProperty("phoneToken")
    public String getPhoneToken() { return phoneToken; }
    @JsonProperty("phoneToken")
    public void setPhoneToken(String value) { this.phoneToken = value; }

    @JsonProperty("bankAccount")
    public String getBankAccount() { return bankAccount; }
    @JsonProperty("bankAccount")
    public void setBankAccount(String value) { this.bankAccount = value; }

    @JsonProperty("bankName")
    public String getBankName() { return bankName; }
    @JsonProperty("bankName")
    public void setBankName(String value) { this.bankName = value; }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }

    @JsonProperty("swipperPhoto")
    public String getSwipperPhoto() { return swipperPhoto; }
    @JsonProperty("swipperPhoto")
    public void setSwipperPhoto(String value) { this.swipperPhoto = value; }

    @JsonProperty("heroPhoto")
    public String getHeroPhoto() { return heroPhoto; }
    @JsonProperty("heroPhoto")
    public void setHeroPhoto(String value) { this.heroPhoto = value; }

    /**********************************************  [ Start Calls ] *************************************************************/

    private String clientID;
    private String agentID;
    private String type;

    @JsonProperty("clientId")
    public String getClientID() { return clientID; }
    @JsonProperty("clientId")
    public void setClientID(String value) { this.clientID = value; }

    @JsonProperty("agentId")
    public String getAgentID() { return agentID; }
    @JsonProperty("agentId")
    public void setAgentID(String value) { this.agentID = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }
}
