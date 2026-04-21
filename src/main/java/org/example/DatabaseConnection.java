package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

        private static final String DB_URL = "jdbc:postgresql://localhost:5433/project_db";
        private static final String USER = "user";
        private static final String PASSWORD = "pass";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }
    }


