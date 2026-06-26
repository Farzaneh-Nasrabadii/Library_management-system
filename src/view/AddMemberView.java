package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import repository.MemberRepository; // Import member database logic
import model.Member; // Import Member model

public class AddMemberView extends JFrame {
    private JTextField nameField, emailField, phoneField;
    private JButton saveButton;

    public AddMemberView() {
        // Window Configuration
        setTitle("👤 Register New Member");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Form Fields
        add(new JLabel(" Full Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel(" Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel(" Phone:"));
        phoneField = new JTextField();
        add(phoneField);

        saveButton = new JButton("Save Member");
        add(saveButton);

        // Action Listener to save member data to PostgreSQL
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();

                if (name.isEmpty()  || email.isEmpty() ||  phone.isEmpty()) {
                    JOptionPane.showMessageDialog(AddMemberView.this, "⚠️ Please fill all fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Save using existing database logic
                MemberRepository memberRepo = new MemberRepository();
                Member newMember = new Member(0, name, email, phone);
                memberRepo.addMember(newMember);

                JOptionPane.showMessageDialog(AddMemberView.this, "✅ Member saved successfully to PostgreSQL!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
    }
}