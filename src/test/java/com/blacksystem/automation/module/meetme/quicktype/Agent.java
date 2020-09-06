package com.blacksystem.automation.module.meetme.quicktype;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Agent {

        private String id;
        private String firstName;
        private String lastName;
        private String secondLastName;
        private String birthDate;
        private String gender;
        private String nickName;
        private String email;
        private String phoneNumber;
        private String ssn;
        private String description;
        private String swipperPhoto;
        private String heroPhoto;
        private String bankAccount;
        private String bankName;
        private String language;
        private String country;
        private boolean emailVerified;
        private boolean active;
        private boolean onLine;
        private boolean onCall;
        private String notes;
        private String phoneToken;
        private String coins;
        private String rate;
        private String totalCalls;
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

        @JsonProperty("phoneNumber")
        public String getPhoneNumber() { return phoneNumber; }
        @JsonProperty("phoneNumber")
        public void setPhoneNumber(String value) { this.phoneNumber = value; }

        @JsonProperty("ssn")
        public String getSsn() { return ssn; }
        @JsonProperty("ssn")
        public void setSsn(String value) { this.ssn = value; }

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

        @JsonProperty("bankAccount")
        public String getBankAccount() { return bankAccount; }
        @JsonProperty("bankAccount")
        public void setBankAccount(String value) { this.bankAccount = value; }

        @JsonProperty("bankName")
        public String getBankName() { return bankName; }
        @JsonProperty("bankName")
        public void setBankName(String value) { this.bankName = value; }

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

        @JsonProperty("onLine")
        public boolean getOnLine() { return onLine; }
        @JsonProperty("onLine")
        public void setOnLine(boolean value) { this.onLine = value; }

        @JsonProperty("onCall")
        public boolean getOnCall() { return onCall; }
        @JsonProperty("onCall")
        public void setOnCall(boolean value) { this.onCall = value; }

        @JsonProperty("notes")
        public String getNotes() { return notes; }
        @JsonProperty("notes")
        public void setNotes(String value) { this.notes = value; }

        @JsonProperty("phoneToken")
        public String getPhoneToken() { return phoneToken; }
        @JsonProperty("phoneToken")
        public void setPhoneToken(String value) { this.phoneToken = value; }

        @JsonProperty("coins")
        public String getCoins() { return coins; }
        @JsonProperty("coins")
        public void setCoins(String value) { this.coins = value; }

        @JsonProperty("rate")
        public String getRate() { return rate; }
        @JsonProperty("rate")
        public void setRate(String value) { this.rate = value; }

        @JsonProperty("totalCalls")
        public String getTotalCalls() { return totalCalls; }
        @JsonProperty("totalCalls")
        public void setTotalCalls(String value) { this.totalCalls = value; }

        @JsonProperty("dateCreated")
        public String getDateCreated() { return dateCreated; }
        @JsonProperty("dateCreated")
        public void setDateCreated(String value) { this.dateCreated = value; }

        @JsonProperty("dateModified")
        public String getDateModified() { return dateModified; }
        @JsonProperty("dateModified")
        public void setDateModified(String value) { this.dateModified = value; }
}

