package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    private static final String URL = "jdbc:postgresql://localhost:5432/Library_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "fr001009";

    public static Connection getConnection() {
        Connection connection = null;
        try {

            Class.forName("org.postgresql.Driver");


            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("🚀 Connected to the PostgreSQL database successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("❌ PostgreSQL JDBC Driver not found. Check your External Libraries!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed! Make sure library_db exists in pgAdmin.");
            e.printStackTrace();
        }
        return connection;
    }
}