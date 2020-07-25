package com.blacksystem.automation.application.properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.blacksystem.automation.application.properties.PropertiesTypes.*;

/**
 * This Class Create a Context Values Initialization for common properties before start the Test Execution
 */
public class ContextProperties {

    private static Logger logger = LogManager.getLogger();
    private static final  Level CONFIG = Level.forName("CONFIG",451);

    /**
     * This variable returns the standard application directory separator.
     */
    public static final String DIRECTORY_SEPARATOR = File.separator;
    private static final String BASE_PATH = Paths.get("").toAbsolutePath().toString();
    private static final String RESOURCES_PATH = BASE_PATH +DIRECTORY_SEPARATOR+"src"+DIRECTORY_SEPARATOR+ "main" + DIRECTORY_SEPARATOR + "resources";
    private static final String SUITE_PATH = RESOURCES_PATH+DIRECTORY_SEPARATOR+"suites";

    //Global Compiling Paths
    private static final String TARGET_DIR=BASE_PATH+DIRECTORY_SEPARATOR+"target";

    //Global System Execution Folder
    private static final String DOWNLOAD_DIR=TARGET_DIR+DIRECTORY_SEPARATOR+"downloads"+DIRECTORY_SEPARATOR;
    private static final String UPLOAD_DIR=RESOURCES_PATH+DIRECTORY_SEPARATOR+"uploads"+DIRECTORY_SEPARATOR;

    //Global System Report
    private static final String REPORT_DIR=TARGET_DIR+DIRECTORY_SEPARATOR+"reports";
    private static final String SCREENSHOTS_DIR=REPORT_DIR+DIRECTORY_SEPARATOR+"screenshots";
    private static final String LOG_DIR=REPORT_DIR+DIRECTORY_SEPARATOR+"logs";
    private static final String TEST_OUTPUT_DIR=BASE_PATH+DIRECTORY_SEPARATOR+"test-output";

    //Mapping Context
    private static String baseUrl;
    private static String logLevel;
    private static String logConsoleEnable;
    private static String parallelMode;

    private static String username;
    private static String password;

    private static String jiraDomain;
    private static String environment;
    private static String database;
    private static String country;
    private static String suite;
    private static String project;
    private static String developmentTeam;

    private static String jobName;
    private static String nodeName;
    private static String buildNumber;
    private static String buildUser;
    private static String supportEmail;
    private static String reportSlack;
    private static String uploadS3;

    public static void main (String[]args){

    }

    /**
     * This method Initialize the Context Properties for Executions
     * @param context ITest Context Share Objects
     */
    public static void setProperties(ITestContext context){

        //Configuration Properties Setup
        ConfigurationProperties.setup();

        //Log Level
        if(getParameter(LOG_LEVEL,context) != null){
            logLevel = getParameter(LOG_LEVEL,context);
            logger.log(CONFIG,"Set Log Level -> TestNG: "+logLevel);
        }else{
            logLevel = ConfigurationProperties.getProperty(LOG_LEVEL);
            if(logLevel == null){
                logLevel = "debug";
                context.setAttribute("logLevel",logLevel);
                logger.log(CONFIG,"Set Log Level -> Property File Default: "+logLevel);
            }else{
                logger.log(CONFIG,"Set Log Level -> Property File: "+logLevel);
            }
        }

        //Log Console Enable
        if(getParameter(LOG_CONSOLE,context) != null){
            logConsoleEnable = getParameter(LOG_CONSOLE,context);
            logger.log(CONFIG,"Set Log Console -> TestNG: "+logConsoleEnable);
        }else{
            logConsoleEnable = ConfigurationProperties.getProperty(LOG_CONSOLE);
            if(logConsoleEnable == null){
                logConsoleEnable = "true";
                context.setAttribute("logConsole",logConsoleEnable);
                logger.log(CONFIG,"Set Log Console -> Property File Default: "+logConsoleEnable);
            }else{
                logger.log(CONFIG,"Set Log Console -> Property File: "+logConsoleEnable);
            }
        }

        //JIRA DOMAIN
        if(getParameter(JIRA_DOMAIN,context) != null){
            jiraDomain = getParameter(JIRA_DOMAIN,context);
            logger.log(CONFIG,"Set Jira Domain -> TestNG: "+jiraDomain);
        }else{
            jiraDomain = ConfigurationProperties.getProperty(JIRA_DOMAIN);
            if(jiraDomain == null){
                jiraDomain = "http://jira.com";
                logger.log(CONFIG,"Set Jira Domain -> Property File Default: "+jiraDomain);
            }else{
                logger.log(CONFIG,"Set Jira Domain -> Property File: "+jiraDomain);
            }
        }

        //Base Url
        if(getParameter(BASE_URL,context) != null){
            baseUrl = getParameter(BASE_URL,context);
            logger.log(CONFIG,"Set Base URL -> TestNG: "+baseUrl);
        }else{
            baseUrl = ConfigurationProperties.getProperty(BASE_URL);
            if(baseUrl == null){
                baseUrl = "http://www.google.com";
                context.setAttribute("baseUrl",baseUrl+DIRECTORY_SEPARATOR);
                logger.log(CONFIG,"Set Base URL -> Property File Default: "+baseUrl);
            }else{
                logger.log(CONFIG,"Set Base URL  -> Property File: "+baseUrl);
            }
        }

        //Username
        if(getParameter(USERNAME,context) != null){
            username = getParameter(USERNAME,context);
            logger.log(CONFIG,"Set Username -> TestNG: "+username);
        }else{
            username = ConfigurationProperties.getProperty(USERNAME);
            if(username == null){
                username = "${USERNAME}";
                context.setAttribute("username",username);
                logger.log(CONFIG,"Set Username -> Property File Default: "+username);
            }else{
                logger.log(CONFIG,"Set Username -> Property File: "+username);
            }
        }

        //Password
        if(getParameter(PASSWORD,context) != null){
            password = getParameter(PASSWORD,context);
            logger.log(CONFIG,"Set Password -> TestNG: "+password);
        }else{
            password = ConfigurationProperties.getProperty(PASSWORD);
            if(password == null){
                password = "${PASSWORD}";
                context.setAttribute("password",password);
                logger.log(CONFIG,"Set Password -> Property File Default: "+password);
            }else{
                logger.log(CONFIG,"Set Password -> Property File: "+password);
            }
        }

        //Environment
        if(getParameter(ENVIRONMENT,context) != null){
            environment = getParameter(ENVIRONMENT,context);
            logger.log(CONFIG,"Set Environment -> TestNG: "+environment);
        }else{
            environment = ConfigurationProperties.getProperty(ENVIRONMENT);
            if(environment == null){
                environment = "${ENVIRONMENT}";
                context.setAttribute("environment",environment);
                logger.log(CONFIG,"Set Environment -> Property File Default: "+environment);
            }else{
                logger.log(CONFIG,"Set Environment -> Property File: "+environment);
            }
        }

        //Database
        if(getParameter(DATABASE,context) != null){
            database = getParameter(DATABASE,context);
            logger.log(CONFIG,"Set Database -> TestNG: "+database);
        }else{
            database = ConfigurationProperties.getProperty(DATABASE);
            if(database == null){
                database = "${DATABASE}";
                context.setAttribute("database",database);
                logger.log(CONFIG,"Set Database -> Property File Default: "+database);
            }else{
                logger.log(CONFIG,"Set Database -> Property File: "+database);
            }
        }

        //Country
        if(getParameter(COUNTRY,context) != null){
            country = getParameter(COUNTRY,context);
            logger.log(CONFIG,"Set Country -> TestNG: "+country);
        }else{
            country = ConfigurationProperties.getProperty(COUNTRY);
            if(country == null){
                country = "${COUNTRY}";
                context.setAttribute("country",country);
                logger.log(CONFIG,"Set Country -> Property File Default: "+country);
            }else{
                logger.log(CONFIG,"Set Country -> Property File: "+country);
            }
        }

        //Suite
        if(getParameter(SUITE,context) != null){
            suite = getParameter(SUITE,context);
            logger.log(CONFIG,"Set Suite -> TestNG: "+suite);
        }else{
            suite = ConfigurationProperties.getProperty(SUITE);
            if(suite == null){
                suite = "${SUITE}";
                context.setAttribute("suite",suite);
                logger.log(CONFIG,"Set Suite -> Property File Default: "+suite);
            }else{
                logger.log(CONFIG,"Set Suite -> Property File: "+suite);
            }
        }

        //Development Team
        if(getParameter(TEAM,context) != null){
            developmentTeam = getParameter(TEAM,context);
            logger.log(CONFIG,"Set Development Team -> TestNG: "+developmentTeam);
        }else{
            developmentTeam = ConfigurationProperties.getProperty(TEAM);
            if(developmentTeam == null){
                developmentTeam = "${TEAM}";
                context.setAttribute("developmentTeam",developmentTeam);
                logger.log(CONFIG,"Set Development Team -> Property File Default: "+developmentTeam);
            }else{
                logger.log(CONFIG,"Set Development Team -> Property File: "+developmentTeam);
            }
        }

        //Project
        if(getParameter(PROJECT,context) != null){
            project = getParameter(PROJECT,context);
            logger.log(CONFIG,"Set Project -> TestNG: "+project);
        }else{
            project = ConfigurationProperties.getProperty(PROJECT);
            if(project == null){
                project = "${PROJECT}";
                context.setAttribute("project",project);
                logger.log(CONFIG,"Set Project -> Property File Default: "+project);
            }else{
                logger.log(CONFIG,"Set Project -> Property File: "+project);
            }
        }

        //Job Name
        if(getParameter(JOB_NAME,context) != null){
            jobName = getParameter(JOB_NAME,context);
            logger.log(CONFIG,"Set Job Name -> TestNG: "+jobName);
        }else{
            jobName = ConfigurationProperties.getProperty(JOB_NAME);
            if(jobName == null){
                jobName = "${JOB_NAME}";
                context.setAttribute("jobName",jobName);
                logger.log(CONFIG,"Set Job Name -> Property File Default: "+jobName);
            }else{
                logger.log(CONFIG,"Set Job Name -> Property File: "+jobName);
            }
        }

        //Node Name
        if(getParameter(NODE_NAME,context) != null){
            nodeName = getParameter(NODE_NAME,context);
            logger.log(CONFIG,"Set Node Name -> TestNG: "+nodeName);
        }else{
            nodeName = ConfigurationProperties.getProperty(NODE_NAME);
            if(nodeName == null){
                nodeName = "${NODE_NAME}";
                context.setAttribute("nodeName",nodeName);
                logger.log(CONFIG,"Set Node Name -> Property File Default: "+nodeName);
            }else{
                logger.log(CONFIG,"Set Node Name -> Property File: "+nodeName);
            }
        }

        //Build Number
        if(getParameter(BUILD_NUMBER,context) != null){
            buildNumber = getParameter(BUILD_NUMBER,context);
            logger.log(CONFIG,"Set Build Number -> TestNG: "+buildNumber);
        }else{
            buildNumber = ConfigurationProperties.getProperty(BUILD_NUMBER);
            if(buildNumber == null){
                buildNumber = "${BUILD_NUMBER}";
                context.setAttribute("buildNumber",buildNumber);
                logger.log(CONFIG,"Set Build Number -> Property File Default: "+buildNumber);
            }else{
                logger.log(CONFIG,"Set Build Number -> Property File: "+buildNumber);
            }
        }

        //Build User
        if(getParameter(BUILD_USER,context) != null){
            buildUser = getParameter(BUILD_USER,context);
            logger.log(CONFIG,"Set Build User -> TestNG: "+buildUser);
        }else{
            buildUser = ConfigurationProperties.getProperty(BUILD_USER);
            if(buildUser == null){
                buildUser = "${BUILD_USER}";
                context.setAttribute("buildUser",buildUser);
                logger.log(CONFIG,"Set Build User -> Property File Default: "+buildUser);
            }else{
                logger.log(CONFIG,"Set Build User -> Property File: "+buildUser);
            }
        }

        //Support Email
        if(getParameter(SUPPORT_EMAIL,context) != null){
            supportEmail = getParameter(SUPPORT_EMAIL,context);
            logger.log(CONFIG,"Set Support Email -> TestNG: "+supportEmail);
        }else{
            supportEmail = ConfigurationProperties.getProperty(SUPPORT_EMAIL);
            if(supportEmail == null){
                supportEmail = "johan.manuel.rosabal@gmail.com";
                context.setAttribute("supportEmail",supportEmail);
                logger.log(CONFIG,"Set Support Email -> Property File Default: "+supportEmail);
            }else{
                logger.log(CONFIG,"Set Support Email -> Property File: "+supportEmail);
            }
        }

        //Report Slack
        if(getParameter(REPORT_SLACK,context) != null){
            reportSlack = getParameter(REPORT_SLACK,context);
            logger.log(CONFIG,"Set Report Slack -> TestNG: "+reportSlack);
        }else{
            reportSlack = ConfigurationProperties.getProperty(REPORT_SLACK);
            if(reportSlack == null){
                reportSlack = "${REPORT_SLACK}";
                context.setAttribute("reportSlack",reportSlack);
                logger.log(CONFIG,"Set Report Slack -> Property File Default: "+reportSlack);
            }else{
                logger.log(CONFIG,"Set Report Slack -> Property File: "+reportSlack);
            }
        }

        //UploadS3
        if(getParameter(UPLOADS3,context) != null){
            uploadS3 = getParameter(UPLOADS3,context);
            logger.log(CONFIG,"Set Upload S3 -> TestNG: "+uploadS3);
        }else{
            uploadS3 = ConfigurationProperties.getProperty(UPLOADS3);
            if(uploadS3 == null){
                uploadS3 = "${REPORT_UPLOAD_S3}";
                context.setAttribute("uploadS3",uploadS3);
                logger.log(CONFIG,"Set Upload S3  -> Property File Default: "+uploadS3);
            }else{
                logger.log(CONFIG,"Set Upload S3  -> Property File: "+uploadS3);
            }
        }
        //Set Parallel Mode
        parallelMode = context.getCurrentXmlTest().getSuite().getParallel().toString();

        // Reset Folders
        setFolders();
    }


    /**
     * This method returns a Level Object for Runtime Change Execution Log Root Level
     * @return Level Object with context configuration set by the user or property default value
     */
    public static Level getLevel(){
        logger.log(CONFIG,"Get Level: "+logLevel);
        return Level.getLevel(logLevel.toUpperCase());
    }

    /**
     * Method that returns the Log Level Definition
     * @return String with the Log Level Test Execution
     */
    public static String getLogLevel(){
        return logLevel.toUpperCase();
    }

    /**
     * Method that returns the Log Console Enable Definition
     * @return String with the Log Console Enable Test Execution
     */
    public static String getLogConsoleEnable(){
        return logConsoleEnable;
    }

    /**
     * Method that returns the Parallel Test Execution Definition
     * @return String with the Parallel Test Execution
     */
    public static String getParallelMode(){
        if(parallelMode == null){
            parallelMode = "none";
        }
        return parallelMode;
    }

    /**
     * Method that returns the Base URL Definition
     * @return String with the Base URL Test Execution
     */
    public static String getBaseUrl(){
        return baseUrl;
    }

    /**
     * Method that returns the Username Definition
     * @return String with the Username Test Execution
     */
    public static String getUsername(){
        return username;
    }

    /**
     * Method that returns the Password Definition
     * @return String with the Password Test Execution
     */
    public static String getPassword(){
        return password;
    }

    /**
     * Method that returns the Environment Definition
     * @return String with the Environment Test Execution
     */
    public static String getEnvironment(){
        return environment;
    }

    /**
     * Method that returns the Data Base Definition
     * @return String with the Date Base Test Execution
     */
    public static String getDatabase(){
        return database;
    }

    /**
     * Method that returns the Country Definition
     * @return String with the Country Test Execution
     */
    public static String getCountry(){
        return country;
    }

    /**
     * Method that returns the Suite Definition
     * @return String with the Suite Test Execution
     */
    public static String getSuite(){
        return suite;
    }

    /**
     * Method that returns the Development Definition
     * @return String with the Development Test Execution
     */
    public static String getDevelopmentTeam(){
        return developmentTeam;
    }

    /**
     * Method that returns the Project Definition
     * @return String with the Project Test Execution
     */
    public static String getProject(){
        return project;
    }

    /**
     * Method that returns the Job Name Definition
     * @return String with the Job Name Test Execution
     */
    public static String getJobName(){
        return jobName;
    }

    /**
     * Method that returns the Node Name Definition
     * @return String with the Node Name Test Execution
     */
    public static String getNodeName(){
        return nodeName;
    }

    /**
     * Method that returns the Build Number Definition
     * @return String with the Build Number Test Execution
     */
    public static String getBuildNumber(){
        return buildNumber;
    }

    /**
     * Method that returns the Build User Definition
     * @return String with the User Test Execution
     */
    public static String getBuild_user(){
        return buildUser;
    }

    /**
     * Method that returns the Email Support Definition
     * @return String with the Email Default for Testing
     */
    public static String getSupport_email(){
        return supportEmail;
    }

    /**
     * Method that returns the Slack Folder Path
     * @return String with the Path Directory
     */
    public static String getReport_slack(){
        return reportSlack;
    }

    /**
     * Method that returns the UploadS2 Folder Path
     * @return String with the Path Directory
     */
    public static String getUploadS3(){
        return uploadS3;
    }

    /**
     * Method that returns the Base Path Folder
     * @return String with the Path Directory
     */
    public static String getBasePath(){
        return BASE_PATH;
    }

    /**
     * Method that returns the Resources Folder Path
     * @return String with the Path Directory
     */
    public static String getResourcesFolderPath(){
        return RESOURCES_PATH;
    }

    /**
     * Method that returns the Suite Folder Path
     * @return String with the Path Directory
     */
    public static String getSuiteFolderPath(){
        return SUITE_PATH;
    }

    /**
     * Method that returns the Download Folder Path
     * @return String with the Path Directory
     */
    public static String getDownloadFolderPath(){
        return DOWNLOAD_DIR;
    }

    /**
     * Method that returns the Upload Folder Path
     * @return String with the Path Directory
     */
    public static String getUploadFolderPath(){
        return UPLOAD_DIR;
    }

    /**
     * Method that returns the Report Folder Path
     * @return String with the Path Directory
     */
    public static String getReportFolderPath(){
        return REPORT_DIR;
    }

    /**
     * Method that returns the Screenshots Folder Path
     * @return String with the Path Directory
     */
    public static String getScreenshotFolderPath(){
        return SCREENSHOTS_DIR;
    }

    /**
     * Method that returns the Log Folder Path
     * @return String with the Path Directory
     */
    public static String getLogFolderPath(){
        return LOG_DIR;
    }

    /**
     * Method that returns the Test Output Folder Path
     * @return String with the Path Directory
     */
    public static String getTestOutputFolderPath(){
        return TEST_OUTPUT_DIR;
    }

    /**
     * Method that returns the Target Folder Path
     * @return String with the Path Directory
     */
    public static String getTargetFolderPath(){
        return TARGET_DIR;
    }

    /**
     * Read context parameter and pass value to context class
     * @param propertiesTypes Specify the Type of Property to get retrieve
     * @param context ITest Context Share Objects
     * @return String Value with the parameter text
     */
    private static String getParameter(PropertiesTypes propertiesTypes,ITestContext context){
        String text = propertiesTypes.toString().replace("\"","").toUpperCase();
        text = context.getSuite().getParameter(text);
        return text;
    }

    public static String getJiraDomain() {
        logger.log(CONFIG,"Get Jira domain: "+jiraDomain);
        return jiraDomain;
    }

    public static void setJiraDomain(String jiraDomain) {
        ContextProperties.jiraDomain = jiraDomain;
    }

    /**
     * Reset Folder Content to Default Before Start Test Execution
     * @param directoryPath Provide a Directory Path
     */
    private static void resetFolder(String directoryPath){
        File file = new File(directoryPath);

        if(file.exists()){
            deleteFolder(directoryPath);
            createFolder(directoryPath);
        }else{
            createFolder(directoryPath);
        }
    }

    /**
     * Method that sets the folder execution:
     * - Delete Target Folder
     * - Delete Output Folder
     * - Create Report Folder with 2 Directories: Screenshots and Logs
     * - Create Download Folder
     * - Create Upload Folder is not Exist
     */
    private static void setFolders(){
        //deleteFolder(TARGET_DIR);
        deleteFolder(TEST_OUTPUT_DIR);

        resetFolder(REPORT_DIR);
        resetFolder(SCREENSHOTS_DIR);
        resetFolder(LOG_DIR);
        resetFolder(DOWNLOAD_DIR);
        createFolder(UPLOAD_DIR);
    }

    /**
     * Created Folder System
     * @param directoryPath String with the path specification
     */
    private static void createFolder(String directoryPath){
        File file = new File(directoryPath);
        file.mkdirs();
    }

    /**
     * Delete a Folder System
     * @param directoryPath String with the path specification
     */
    private static void deleteFolder(String directoryPath){
        File file = new File(directoryPath);
        if(file.exists()){
            try {
                Path path = Paths.get(directoryPath);
                Files.walk(path).sorted().map(Path::toFile).forEach(File::delete);
                file.delete();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
