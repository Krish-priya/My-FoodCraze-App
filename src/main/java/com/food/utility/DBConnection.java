package com.food.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/food_delivery_app";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "Krish@25";   // Change according to your MySQL password

    private static Connection connection;

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(
                        URL,
                        USERNAME,
                        PASSWORD
                );
            }

        } catch (ClassNotFoundException e) {

            System.out.println("MySQL Driver Not Found");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Database Connection Failed");
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnection() {

        try {

            if (connection != null && !connection.isClosed()) {

                connection.close();

                System.out.println("Database Connection Closed");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}