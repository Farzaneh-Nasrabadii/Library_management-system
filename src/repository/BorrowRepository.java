package repository;

import model.Borrow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrowRepository {

    // Method to record a book borrowing transaction
    public void javaBorrowBook(Borrow borrow) {
        String query = "INSERT INTO borrows(member_id, book_id, borrow_date, return_date, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, borrow.getMemberId());
            pstmt.setInt(2, borrow.getBookId());
            pstmt.setDate(3, new java.sql.Date(borrow.getBorrowDate().getTime()));

            if (borrow.getReturnDate() != null) {
                pstmt.setDate(4, new java.sql.Date(borrow.getReturnDate().getTime()));
            } else {
                pstmt.setNull(4, java.sql.Types.DATE);
            }

            pstmt.setString(5, borrow.getStatus());

            pstmt.executeUpdate();
            System.out.println("📖 Borrowing record saved to database successfully!");

        } catch (SQLException e) {
            System.out.println("❌ Error while saving borrowing record!");
            e.printStackTrace();
        }
    }
}