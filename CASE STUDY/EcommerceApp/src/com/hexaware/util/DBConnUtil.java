package com.hexaware.util;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.lang.ClassNotFoundException;


public class DBConnUtil
{

    // Method for the connection with the database
    public static Connection getConnection() {
            try {
                Properties props = DBPropertyUtil.getPropertyString();
                
                // Load the MySQL driver
                Class.forName(props.getProperty("db.driver"));

                // Establish the connection
                Connection connection = DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.username"),
                    props.getProperty("db.password")
                );

                System.out.println(" Database connected successfully!");
                return connection;
                
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println(" Database connection failed!");
                return null;
            }
        }
       
    }
    


