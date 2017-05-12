package ru.sellersp.api.modules;

import org.jvnet.hk2.annotations.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class PropertiesModule {

    private Properties properties;

    public Properties provideProperties() {
        properties = loadProperties();
        return properties;
    }

    public void reload(){
        properties = loadProperties();
    }

    private Properties loadProperties(){
        Properties props = new Properties();
        try {
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/application.properties");
            props.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
