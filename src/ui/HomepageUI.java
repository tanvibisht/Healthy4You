package ui;

import domain.User;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class HomepageUI {
    private UserService userService;
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Consumer<User> onLoginSuccess;

    public HomepageUI(UserService userService) {
        this.userService = userService;
        initializeUI();
    }

    public void setOnLoginSuccess(Consumer<User> onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    private void initializeUI() {
        frame = new JFrame("Healthy4You - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Signup");

        loginButton.addActionListener(this::loginAction);
        signupButton.addActionListener(this::signupAction);

        frame.add(new JLabel("Username:"));
        frame.add(usernameField);
        frame.add(new JLabel("Password:"));
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(signupButton);

        frame.setVisible(true);
    }

    private void loginAction(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        User user = userService.loginUser(username, password);
        if (user != null) {
            frame.dispose();
            onLoginSuccess.accept(user);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid login!");
        }
    }

    private void signupAction(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        User user = userService.createUser(username, password);
        if (user != null) {
            frame.dispose();
            onLoginSuccess.accept(user);
        } else {
            JOptionPane.showMessageDialog(frame, "Signup failed: User already exists.");
        }
    }
}
