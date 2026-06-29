package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import repository.DatabaseConnection; // Using your exact database connection package

public class ViewMembersView extends JFrame {
    private JTable membersTable;
    private DefaultTableModel tableModel;

    public ViewMembersView() {
        // Window Configuration
        setTitle("👤 View All Members");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Define columns for the table based on your DB schema
        String[] columnNames = {"Member ID", "Name", "Email", "Phone"};
        tableModel = new DefaultTableModel(columnNames, 0);
        membersTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(membersTable);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch data from PostgreSQL
        loadMembersData();
    }

    private void loadMembersData() {
        // SQL Query to match your table columns (adjust name/phone if different in your DB)
        String query = "SELECT id, name, email, phone FROM members ORDER BY id ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                // Add row to the GUI table
                tableModel.addRow(new Object[]{id, name, email, phone});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error loading members data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}