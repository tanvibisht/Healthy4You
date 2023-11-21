package Dashboard;

import UserSignUp.SignupGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI extends JFrame {
    private JButton signupButton;
    private JButton loginButton;

    public MainMenuGUI() {
        createUI();
        setupUI();
    }

    private void createUI() {
        signupButton = new JButton("Sign Up");
        loginButton = new JButton("Log In");

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignupGUI().setVisible(true);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainMenuGUI.this,
                        "Login not implemented yet.");
            }
        });
    }

    private void setupUI() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(signupButton);
        add(loginButton);
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenuGUI().setVisible(true);
            }
        });
    }
}
