package com.blacksystem.automation.application.properties;

public enum PropertiesTypes {

    LOG_LEVEL("log_level"),
    LOG_CONSOLE("log_console"),
    BASE_URL("base_url"),
    JIRA_DOMAIN("jira_domain"),
    USERNAME("username"),
    PASSWORD("password"),
    ENVIRONMENT("environment"),
    DATABASE("database"),
    COUNTRY("country"),
    SUITE("suite"),
    TEAM("team"),
    PROJECT("project"),
    JOB_NAME("job_name"),
    NODE_NAME("node_name"),
    BUILD_NUMBER("build_number"),
    BUILD_USER("build_user"),
    SUPPORT_EMAIL("support_email"),
    REPORT_SLACK("report_slack"),
    UPLOADS3("report_upload");

    private String text;

    PropertiesTypes(String text) {
        this.text = text;
    }

    public boolean equalName(String otherName){
        return text.equalsIgnoreCase(otherName);
    }

    @Override
    public String toString(){
        return this.text;
    }
}
