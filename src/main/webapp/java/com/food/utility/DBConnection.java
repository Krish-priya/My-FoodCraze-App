package com.food.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{

    private static final String URL      = "jdbc:mysql://localhost:3306/food_delivery_app";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Krish@25";

    private DBConnection() {}   // prevent instantiation

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
    }
}
