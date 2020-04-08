package Model.Connector;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private String driver;
    private String url;
    private String name;
    private String password;
    private String fileConfigName = "config.properties";

    public ConfigurationManager() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(fileConfigName);
        properties.load(fis);
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        name = properties.getProperty("name");
        password = properties.getProperty("password");
    }


    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
