package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import repository.BorrowRepository;

public class ReturnBookView extends JFrame {
    private JTextField memberIdField, bookIdField;
    private JButton returnButton;

    public ReturnBookView() {
        // Window Configuration
        setTitle("🔄 Return Borrowed Book");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Form Fields
        add(new JLabel(" Member ID:"));
        memberIdField = new JTextField();
        add(memberIdField);

        add(new JLabel(" Book ID:"));
        bookIdField = new JTextField();
        add(bookIdField);

        returnButton = new JButton("Return Book");
        add(returnButton);

        // Action Listener to handle return logic based on Member ID and Book ID
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memberIdText = memberIdField.getText();
                String bookIdText = bookIdField.getText();

                if (memberIdText.isEmpty() || bookIdText.isEmpty()) {
                    JOptionPane.showMessageDialog(ReturnBookView.this, "⚠️ Please fill all fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int memberId = Integer.parseInt(memberIdText);
                    int bookId = Integer.parseInt(bookIdText);

                    // Call your exact database method: returnBook(memberId, bookId)
                    BorrowRepository borrowRepo = new BorrowRepository();
                    borrowRepo.returnBook(memberId, bookId);

                    JOptionPane.showMessageDialog(ReturnBookView.this, "✅ Book transaction processed! Check terminal for status.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ReturnBookView.this, "❌ IDs must be valid numbers!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }); // Properly closed ActionListener
    } // Properly closed Constructor
}