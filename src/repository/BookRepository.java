package repository;

import model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    // Method to add a new book to the database
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

    // Method to search books by Title, Author, or exact ISBN
    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE title ILIKE ? OR author ILIKE ? OR isbn = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setString(3, keyword);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getInt("book_id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("isbn"),
                            rs.getInt("total_copies"),
                            rs.getInt("available_copies")
                    );
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error while searching for books!");
            e.printStackTrace();
        }
        return books;
    }
}