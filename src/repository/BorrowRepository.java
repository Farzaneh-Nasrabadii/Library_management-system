package repository;

import model.Borrow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowRepository {

    // Method to record borrowing AND decrease available copies automatically
    public void javaBorrowBook(Borrow borrow) {
        String checkQuery = "SELECT available_copies FROM books WHERE book_id = ?";
        String insertQuery = "INSERT INTO borrows (member_id, book_id, borrow_date, return_date, status) VALUES (?, ?, ?, ?, ?)";
        String updateBookQuery = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?";

        // Since DatabaseConnection is in the same package (repository), we call it directly
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Disable auto-commit for Transaction Management (ACID principles)
            conn.setAutoCommit(false);

            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                 PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                 PreparedStatement updateStmt = conn.prepareStatement(updateBookQuery)) {

                // 1. Check if book is available
                checkStmt.setInt(1, borrow.getBookId());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        int available = rs.getInt("available_copies");
                        if (available <= 0) {
                            System.out.println("⚠️ Sorry! This book is currently out of stock.");
                            conn.rollback();
                            return;
                        }
                    } else {
                        System.out.println("❌ Error: Book ID not found.");
                        conn.rollback();
                        return;
                    }
                }

                // 2. Insert into borrows table (Using clean SQL Dates to avoid getter issues)
                insertStmt.setInt(1, borrow.getMemberId());
                insertStmt.setInt(2, borrow.getBookId());
                insertStmt.setDate(3, new java.sql.Date(new java.util.Date().getTime())); // Current date for borrowing
                insertStmt.setNull(4, java.sql.Types.DATE); // Return date is null at the beginning
                insertStmt.setString(5, "BORROWED"); // Default status
                insertStmt.executeUpdate();

                // 3. Decrease available copies in books table
                updateStmt.setInt(1, borrow.getBookId());
                updateStmt.executeUpdate();

                // Commit transaction if all steps succeed
                conn.commit();
                System.out.println("📖 Book successfully issued and inventory updated (-1)!");

            } catch (SQLException e) {
                conn.rollback(); // Undo everything if any error occurs
                System.out.println("❌ Transaction failed! Changes rolled back.");
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println("❌ Database connection error!");
            e.printStackTrace();
        }
    }

    // Method to return book AND increase available copies automatically
    public void returnBook(int memberId, int bookId) {
        String updateBorrowQuery = "UPDATE borrows SET return_date = ?, status = 'RETURNED' WHERE member_id = ? AND book_id = ? AND status = 'BORROWED'";
        String updateBookQuery = "UPDATE books SET available_copies = available_copies + 1 WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement borrowStmt = conn.prepareStatement(updateBorrowQuery);
                 PreparedStatement bookStmt = conn.prepareStatement(updateBookQuery)) {

                // 1. Update borrow record
                borrowStmt.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
                borrowStmt.setInt(2, memberId);
                borrowStmt.setInt(3, bookId);
                int rows = borrowStmt.executeUpdate();

                if (rows == 0) {
                    System.out.println("⚠️ No active borrowing record found for this Member and Book.");
                    conn.rollback();
                    return;
                }

                // 2. Increase available copies in books table
                bookStmt.setInt(1, bookId);
                bookStmt.executeUpdate();

                conn.commit();
                System.out.println("✅ Book successfully returned and inventory updated (+1).");

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("❌ Return transaction failed!");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}