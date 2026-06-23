package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import repository.BookRepository;
import model.Book;

public class AddBookView extends JFrame {
    private JTextField titleField, authorField, isbnField, copiesField;
    private JButton saveButton;

    public AddBookView() {
        // Window Configuration
        setTitle("➕ Register New Book");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Form Fields
        add(new JLabel(" Title:"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel(" Author:"));
        authorField = new JTextField();
        add(authorField);

        add(new JLabel(" ISBN:"));
        isbnField = new JTextField();
        add(isbnField);

        add(new JLabel(" Total Copies:"));
        copiesField = new JTextField();
        add(copiesField);

        saveButton = new JButton("Save to Database");
        add(saveButton);

        // Action Listener to handle data persistence
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String isbn = isbnField.getText();

                if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || copiesField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(AddBookView.this, "⚠️ Please fill all fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int copies = Integer.parseInt(copiesField.getText());

                    // Interact with our database logic
                    BookRepository bookRepo = new BookRepository();
                    Book newBook = new Book(0, title, author, isbn, copies, copies);
                    bookRepo.addBook(newBook);

                    JOptionPane.showMessageDialog(AddBookView.this, "✅ Book saved successfully to PostgreSQL!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddBookView.this, "❌ Total Copies must be a valid number!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}