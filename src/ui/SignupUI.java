package ui;

import org.json.JSONException;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SignupUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton createAccountButton;

    public SignupUI() {
        // Initialize components and layout
        setTitle("Signup");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        createAccountButton = new JButton("Create Account");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(createAccountButton);

        // Add action listener
        createAccountButton.addActionListener(e -> createAccount());

        pack();
        setVisible(true);
    }

    private void createAccount() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        UserService userService = new UserService();
        try {
            if (!userService.userExists(username)) {
                userService.saveUser(username, password);
                // Account created successfully, you can either open Dashboard or redirect to login
                new DashboardUI();
                this.dispose(); // Close the signup window
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists.", "Signup Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle exceptions properly in production code
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
