package com.seleniummastercucumber.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author : user
 * @created : 8.12.2023,16:40
 * @Email :aliyeidiris@gmail.com
 **/
public class FileUtility {
    public static String readConfig(String key){
        final String configPath="config.properties";
        Properties properties =new Properties();
        try {
            properties.load(new FileInputStream(configPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);
    }
}
