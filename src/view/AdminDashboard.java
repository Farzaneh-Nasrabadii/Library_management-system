package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        // 1. Setup Dashboard Window
        setTitle("👑 Librarian Admin Panel");
        setSize(500, 450); // Slightly increased height for the new button row
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // 2. Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185)); // Deep Tech Blue
        JLabel headerTitle = new JLabel("Library Management Dashboard");
        headerTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerTitle.setForeground(Color.WHITE);
        headerPanel.add(headerTitle);
        add(headerPanel, BorderLayout.NORTH);

        // 3. Grid Panel for Buttons (Changed to 3 Rows, 2 Columns to fit the new button)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAddBook = new JButton("📚 Register New Book");
        JButton btnAddMember = new JButton("👤 Register New Member");
        JButton btnIssueBook = new JButton("📖 Issue / Borrow Book");
        JButton btnReturnBook = new JButton("🔄 Return Borrowed Book");
        JButton btnViewBooks = new JButton("👁️ View All Books"); // Successfully defined the missing button

        // Style the buttons slightly
        Font btnFont = new Font("Arial", Font.PLAIN, 14);
        btnAddBook.setFont(btnFont);
        btnAddMember.setFont(btnFont);
        btnIssueBook.setFont(btnFont);
        btnReturnBook.setFont(btnFont);
        btnViewBooks.setFont(btnFont);

        // Link the "Register New Book" button to the AddBookView window
        btnAddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBookView addBookWindow = new AddBookView();
                addBookWindow.setVisible(true);
            }
        });

        // Link the "Register New Member" button to the AddMemberView window
        btnAddMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMemberView addMemberWindow = new AddMemberView();
                addMemberWindow.setVisible(true);
            }
        });

        // Link the "Issue / Borrow Book" button to the IssueBookView window
        btnIssueBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IssueBookView issueBookWindow = new IssueBookView();
                issueBookWindow.setVisible(true);
            }
        });

        // Link the "Return Borrowed Book" button to the ReturnBookView window
        btnReturnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReturnBookView returnWindow = new ReturnBookView();
                returnWindow.setVisible(true);
            }
        });

        // Link the "View Books" button to the ViewBooksView window
        btnViewBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewBooksView viewBooksWindow = new ViewBooksView();
                viewBooksWindow.setVisible(true);
            }
        });

        // Add all 5 buttons to the grid panel
        buttonPanel.add(btnAddBook);
        buttonPanel.add(btnAddMember);
        buttonPanel.add(btnIssueBook);
        buttonPanel.add(btnReturnBook);
        buttonPanel.add(btnViewBooks); // Added successfully to the GUI layout
        add(buttonPanel, BorderLayout.CENTER);

        // 4. Footer / Status Panel
        JPanel footerPanel = new JPanel();
        JButton btnLogout = new JButton("🚪 Logout");
        footerPanel.add(btnLogout);
        add(footerPanel, BorderLayout.SOUTH);

        // 5. Logout Action
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close dashboard
                // If LoginView exists, it will open, otherwise you can comment this line
                // new LoginView().setVisible(true);
            }
        });
    }
}