package ui;

import org.json.JSONException;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;

    public LoginUI() {
        // Initialize components and layout
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(signupButton);

        // Add action listeners
        loginButton.addActionListener(e -> performLogin());
        signupButton.addActionListener(e -> openSignupForm());

        pack();
        setVisible(true);
    }
    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        UserService userService = new UserService();
        try {
            if (userService.validateLogin(username, password)) {
                // Login successful, open Dashboard
                new DashboardUI();
                this.dispose(); // Close the login window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle exceptions properly in production code
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    private void openSignupForm() {
        new SignupUI(); // Open the signup form
        this.dispose(); // Optionally close the login form
    }


}
