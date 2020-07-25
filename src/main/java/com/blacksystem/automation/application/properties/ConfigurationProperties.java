package com.blacksystem.automation.application.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

/**
 * This Class Extracts from "configuration.properties" file on Resources the Default Values Before Application Initialize the Context Methods and Properties
 */
public class ConfigurationProperties {

    private static String result;
    private static InputStream inputStream;
    private static Logger logger = LogManager.getLogger();
    private static final  Level CONFIG = Level.forName("CONFIG",451);
    private static Properties props = new Properties();

    /**
     * Private Constructor
     */
    private ConfigurationProperties(){}

   public static void setup(){
       try {
           inputStream = new FileInputStream("src/main/resources/configuration.properties");
           logger.log(CONFIG,""+inputStream.toString());
       } catch (FileNotFoundException e) {
           logger.fatal("FATAL ERROR: "+e.getMessage());
       }
   }

    /**
     * This method returns the Property Value from the Global Property File
     * @param propertiesTypes Specify the Type of Property to get retrieve
     * @return String value with the property value
     */
       public static String getProperty(@NotNull PropertiesTypes propertiesTypes){

            try{
                String property = propertiesTypes.toString().toLowerCase();

                props.load(inputStream);
                result =  props.getProperty(property);

                if(result != null){
                    result = result.replace("\"","");
                }
                logger.log(CONFIG,"Getting Property: "+property+" : "+result);
                return result;
            }catch(FileNotFoundException e){
                logger.fatal("Get Property File " + "[\"configuration.properties\"]" + " not found in the classpath:"+e.getMessage());
            } catch (IOException b) {
                logger.fatal("Get Property File IOException: " + "[\"configuration.properties\"]" + " not found in the classpath:"+b.getMessage());
            }
            return result;
       }


}
