import repository.DatabaseConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Library Management System...");


        Connection conn = DatabaseConnection.getConnection();


        if (conn != null) {
            System.out.println("✅ Database connection test passed!");
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("❌ Database connection test failed.");
        }
    }
}