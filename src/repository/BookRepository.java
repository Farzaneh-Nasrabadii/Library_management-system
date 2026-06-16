package repository;

import model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookRepository {


    public void addBook(Book book) {

        String query = "INSERT INTO books (title, author, isbn, total_copies, available_copies) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {


            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getIsbn());
            pstmt.setInt(4, book.getTotalCopies());
            pstmt.setInt(5, book.getAvailableCopies());


            pstmt.executeUpdate();
            System.out.println("📚 Book added to database successfully: " + book.getTitle());

        } catch (SQLException e) {
            System.out.println("❌ Error while adding book to database!");
            e.printStackTrace();
        }
    }
}