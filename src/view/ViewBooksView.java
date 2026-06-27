package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import repository.DatabaseConnection; // Adjust this import based on your actual database package

public class ViewBooksView extends JFrame {
    private JTable booksTable;
    private DefaultTableModel tableModel;

    public ViewBooksView() {
        // Window Configuration
        setTitle("📚 View All Books");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Define columns for the table
        String[] columnNames = {"Book ID", "Title", "Author", "ISBN", "Available Copies"};
        tableModel = new DefaultTableModel(columnNames, 0);
        booksTable = new JTable(tableModel);

        // Add table to a scroll pane (so user can scroll if there are many books)
        JScrollPane scrollPane = new JScrollPane(booksTable);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch data from PostgreSQL and populate the table
        loadBooksData();
    }

    private void loadBooksData() {
        String query = "SELECT book_id, title, author, isbn, available_copies FROM books ORDER BY book_id ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Loop through the database results and add rows to the table
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String isbn = rs.getString("isbn");
                int copies = rs.getInt("available_copies");

                // Add row to the GUI table model
                tableModel.addRow(new Object[]{id, title, author, isbn, copies});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error loading books data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}