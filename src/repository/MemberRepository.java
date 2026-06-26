package repository;

import model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    // Method to add a new member to PostgreSQL
    public void addMember(Member member) {
        String query = "INSERT INTO members (first_name, last_name, phone) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, member.getFirstName());
            pstmt.setString(2, member.getLastName());
            pstmt.setString(3, member.getPhoneNumber());

            pstmt.executeUpdate();
            System.out.println("👤 Member registered successfully: " + member.getFirstName() + " " + member.getLastName());

        } catch (SQLException e) {
            System.out.println("❌ Error while registering member in database!");
            e.printStackTrace();
        }
    }

    // Method to fetch all members for the Admin report
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM members ORDER BY member_id ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Member member = new Member(
                        rs.getInt("member_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number")
                );
                members.add(member);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error while fetching members list from database!");
            e.printStackTrace();
        }
        return members;
    }
}