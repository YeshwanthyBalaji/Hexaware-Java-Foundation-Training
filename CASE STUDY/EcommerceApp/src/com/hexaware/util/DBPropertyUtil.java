package com.hexaware.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil
{
	public static Properties getPropertyString() {
        Properties properties = new Properties();
        
        //FileInputStream reads the db.properties file
        try (FileInputStream input = new FileInputStream("src/db.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return properties;
	}
}
