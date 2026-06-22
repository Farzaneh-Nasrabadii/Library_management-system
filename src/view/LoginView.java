package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    // GUI Components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginView() {
        // 1. Setup Window Properties
        setTitle("Library System - Secure Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the window on the screen
        setLayout(new GridBagLayout()); // GridBagLayout handles centering perfectly

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 2. Create and Add Components to Layout

        // Header Title Label
        JLabel titleLabel = new JLabel("📚 System Authentication", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Username Label & Input Field
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        add(userLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        add(usernameField, gbc);

        // Password Label & Input Field
        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0; gbc.gridy = 2;
        add(passLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 2;
        add(passwordField, gbc);

        // Login Button
        loginButton = new JButton("🔑 Login As Admin");
        loginButton.setBackground(new Color(46, 204, 113)); // Tech Green
        loginButton.setForeground(Color.BLACK);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(loginButton, gbc);

        // 3. Setup Button Event Listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    // Complete Business Logic for Authentication & Window Transition
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Strict validation: Checks both username AND password
        if (username.equals("admin") && password.equals("admin123")) {
            JOptionPane.showMessageDialog(this, "✅ Access Granted! Welcome Admin.", "Success", JOptionPane.INFORMATION_MESSAGE);

            this.dispose(); // Securely close and destroy the current login window

            // Open the new AdminDashboard window smoothly
            AdminDashboard dashboard = new AdminDashboard();
            dashboard.setVisible(true);

        } else {
            // Display an error pop-up if authentication fails
            JOptionPane.showMessageDialog(this, "❌ Incorrect Username or Password! Access Denied.", "Authentication Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}