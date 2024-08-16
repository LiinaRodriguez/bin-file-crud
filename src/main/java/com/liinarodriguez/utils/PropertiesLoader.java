package com.liinarodriguez.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("./src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot read properties file");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}