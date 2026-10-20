package com.shopsphere.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DB {
    private static final String URL = "jdbc:mysql://localhost:3306/shopsphere?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    // Set your local MySQL password here after fixing Workbench login. Never commit it to GitHub.
    private static final String PASSWORD = "CHANGE_ME";

    private DB() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
