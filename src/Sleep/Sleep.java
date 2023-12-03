package Sleep;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Sleep extends JFrame {
    private JTextField hourField, minuteField, usernameField;
    private JButton submitButton, clearButton;
    private static final String FILE_PATH = "src/sleep.txt";

    public Sleep() {
        createUI();
    }

    private void createUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        usernameField = new JTextField(20);
        hourField = new JTextField(2);
        minuteField = new JTextField(2);
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeSleepData();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearSleepData();
            }
        });

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Hours:"));
        add(hourField);
        add(new JLabel("Minutes:"));
        add(minuteField);
        add(submitButton);
        add(clearButton);

        pack();
        setVisible(true);
    }

    private void writeSleepData() {
        String username = usernameField.getText();
        String hours = hourField.getText();
        String minutes = minuteField.getText();

        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(username + ": " + hours + " hours " + minutes + " minutes");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void clearSleepData() {
        hourField.setText("");
        minuteField.setText("");
    }

    public static void main(String[] args) {
        new Sleep();
    }
}
