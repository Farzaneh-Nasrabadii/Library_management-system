package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import repository.DatabaseConnection;

public class ViewMembersView extends JFrame {
    private JTable membersTable;
    private DefaultTableModel tableModel;

    public ViewMembersView() {
        setTitle("👥 View All Members");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table headers matching your real data layout nicely
        String[] columnNames = {"Member ID", "Full Name", "Phone", "Join Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        membersTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(membersTable);
        add(scrollPane, BorderLayout.CENTER);

        loadMembersData();
    }

    private void loadMembersData() {
        // Updated query to match your exact PostgreSQL columns from pgAdmin
        String query = "SELECT member_id, first_name, last_name, phone, join_date FROM members ORDER BY member_id ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("member_id");
                // Combine first_name and last_name for a cleaner UI look
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                String phone = rs.getString("phone");
                String joinDate = rs.getString("join_date");

                tableModel.addRow(new Object[]{id, fullName, phone, joinDate});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error loading members data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}