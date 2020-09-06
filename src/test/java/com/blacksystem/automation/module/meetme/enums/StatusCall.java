package com.blacksystem.automation.module.meetme.enums;

public enum StatusCall {

    COMPLETED("COMPLETED"),
    IN_PROGRESS("IN_PROGRESS"),
    PENDING("PENDING"),
    FAIL_CONNECTION("FAIL_CONNECTION");

    private final String name;

    StatusCall(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    public static StatusCall fromString(String status){
        for(StatusCall e: StatusCall.values()){
            if(e.name().toLowerCase().equalsIgnoreCase(status)){
                return e;
            }
        }
        try {
            throw new Exception("Status Type no supported ["+status+"]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
