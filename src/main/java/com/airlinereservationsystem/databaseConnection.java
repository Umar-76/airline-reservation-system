package com.airlinereservationsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class databaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "umardb";
        String databaseUser = "root";
        String databasePassword = "Umar@abd20996";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }
        catch (Exception e){
            e.getCause();
            e.getStackTrace();
        }
        return databaseLink;
    }
}
