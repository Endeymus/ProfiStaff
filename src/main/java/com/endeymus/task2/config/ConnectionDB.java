package com.endeymus.task2.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Класс обеспечивающий доступ к БД
 * @author Mark Shamray
 */
public class ConnectionDB {
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("db/db");
        String url = resource.getString("url");
        String user = resource.getString("username");
        String pass = resource.getString("password");

        return DriverManager.getConnection(url, user, pass);
    }
}
