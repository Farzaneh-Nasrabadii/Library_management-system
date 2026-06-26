package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import repository.BorrowRepository;
import model.Borrow;

public class IssueBookView extends JFrame {
    private JTextField bookIdField, memberIdField;
    private JButton issueButton;

    public IssueBookView() {
        // Window Configuration
        setTitle("📖 Issue / Borrow Book");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Form Fields
        add(new JLabel(" Book ID:"));
        bookIdField = new JTextField();
        add(bookIdField);

        add(new JLabel(" Member ID:"));
        memberIdField = new JTextField();
        add(memberIdField);

        issueButton = new JButton("Issue Book");
        add(issueButton);

        // Action Listener to handle issuing logic in PostgreSQL
        issueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookIdText = bookIdField.getText();
                String memberIdText = memberIdField.getText();

                if (bookIdText.isEmpty() || memberIdText.isEmpty()) {
                    JOptionPane.showMessageDialog(IssueBookView.this, "⚠️ Please fill all fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int bookId = Integer.parseInt(bookIdText);
                    int memberId = Integer.parseInt(memberIdText);

                    // Create Borrow object with the exact 6 parameters required by your model:
                    // (id, memberId, bookId, loanDate, returnDate, status)
                    Borrow borrowRecord = new Borrow(
                            0,
                            memberId,
                            bookId,
                            new java.util.Date(),
                            null,
                            "BORROWED"
                    );

                    // Call the exact method from BorrowRepository
                    BorrowRepository borrowRepo = new BorrowRepository();
                    borrowRepo.javaBorrowBook(borrowRecord);

                    JOptionPane.showMessageDialog(IssueBookView.this, "✅ Book issued successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(IssueBookView.this, "❌ IDs must be valid numbers!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}