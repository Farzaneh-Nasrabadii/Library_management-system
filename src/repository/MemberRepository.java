package repository;

import model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberRepository {

    // Method to add a new library member to the database
    public void addMember(Member member) {
        String query = "INSERT INTO members (first_name, last_name, phone, join_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, member.getFirstName());
            pstmt.setString(2, member.getLastName());
            pstmt.setString(3, member.getPhone());
            // Convert java.util.Date to java.sql.Date for PostgreSQL
            pstmt.setDate(4, new java.sql.Date(member.getJoinDate().getTime()));

            pstmt.executeUpdate();
            System.out.println("👤 Member added to database successfully: " + member.getFirstName() + " " + member.getLastName());

        } catch (SQLException e) {
            System.out.println("❌ Error while adding member to database!");
            e.printStackTrace();
        }
    }
}