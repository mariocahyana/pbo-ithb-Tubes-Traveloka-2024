package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    private Connection con;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/travelapp";
    private final String username = "root";
    private final String password = "";

    public void connect() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName(driver);
                con = DriverManager.getConnection(url, username, password);
                System.out.println("Database connected successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database.", e);
        }
    }

    public Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void disconnect() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}