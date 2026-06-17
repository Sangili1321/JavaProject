package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

public class ConfigReader {
    Properties prop = new Properties();
    private static final Logger Logger = LoggerUtil.getLogger(ConfigReader.class);
    private String env;

    public ConfigReader(String environment) {
        this.env = environment;
        try {
            FileReader file = new FileReader("C:\\Users\\sduraia\\AllProjects\\CucumberProject\\src\\test\\java\\configfiles\\baseconfig.properties");
            prop.load(file);
            Logger.info("Reading the config file success");
        } catch (IOException e) {
            Logger.error("File Not found for reading the config");
        }
    }

    public String GetUrl(){
        return prop.getProperty("url"+env);
    }
    public String GetUserName(){
        return prop.getProperty("username"+env);
    }
    public String GetPassword(){
        return prop.getProperty("password"+env);
    }


}
