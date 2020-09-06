package com.blacksystem.automation.module.meetme.enums;

public enum TypeCall {
    PRIVATE("PRIVATE"),
    SHARED("SHARED");

    private String type;

    TypeCall(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }

    public static TypeCall fromString(String type){
        for(TypeCall e: TypeCall.values()){
            if(e.name().toLowerCase().equalsIgnoreCase(type)){
                return e;
            }
        }
        try {
            throw new Exception("Call Type no supported ["+type+"]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
