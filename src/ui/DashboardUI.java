package ui;

import service.UserService;
import service.WeatherService;

import javax.swing.*;
import java.awt.*;

public class DashboardUI {
    private final UserService userService;
    private final WeatherService weatherService;
    private JFrame frame;
    private JTextField usernameField, cityField;
    private JPasswordField passwordField;
    private JButton submitButton, weatherButton;
    private JTextArea weatherArea;

    public DashboardUI(UserService userService, WeatherService weatherService) {
        this.userService = userService;
        this.weatherService = weatherService;
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Healthy4You");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Set the blue theme color
        Color blueTheme = Color.decode("#3A9BDC"); // Royal Blue color
        frame.getContentPane().setBackground(blueTheme);

        // User input panel
        JPanel userInputPanel = new JPanel();
        userInputPanel.setLayout(new GridLayout(0, 2, 10, 10));
        userInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        userInputPanel.setOpaque(false); // Use the frame's background color

        userInputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        userInputPanel.add(usernameField);

        userInputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        userInputPanel.add(passwordField);

        submitButton = new JButton("Create User");
        styleButton(submitButton, blueTheme);
        userInputPanel.add(submitButton);
        userInputPanel.add(new JLabel()); // Placeholder

        // Weather input panel
        JPanel weatherInputPanel = new JPanel();
        weatherInputPanel.setLayout(new GridLayout(0, 2, 10, 10));
        weatherInputPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        weatherInputPanel.setOpaque(false);

        weatherInputPanel.add(new JLabel("Enter city for weather:"));
        cityField = new JTextField();
        weatherInputPanel.add(cityField);

        weatherButton = new JButton("Get Weather");
        styleButton(weatherButton, blueTheme);
        weatherInputPanel.add(weatherButton);
        weatherInputPanel.add(new JLabel()); // Placeholder

        // Combine panels
        JPanel combinedPanel = new JPanel(new BorderLayout());
        combinedPanel.add(userInputPanel, BorderLayout.NORTH);
        combinedPanel.add(weatherInputPanel, BorderLayout.SOUTH);
        combinedPanel.setOpaque(false);

        frame.add(combinedPanel, BorderLayout.NORTH);

        // Weather display area
        weatherArea = new JTextArea(5, 20);
        weatherArea.setWrapStyleWord(true);
        weatherArea.setLineWrap(true);
        weatherArea.setEnabled(false);
        weatherArea.setDisabledTextColor(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(weatherArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setSize(600, 400); // Set a larger initial size
        frame.setLocationRelativeTo(null); // Center the window

        submitButton.addActionListener(e -> createUser());
        weatherButton.addActionListener(e -> showWeather());
    }

    private void styleButton(JButton button, Color themeColor) {
        button.setBackground(themeColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
    }

    public void display() {
        frame.setVisible(true);
    }

    private void createUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        userService.createUser(username, password);
        JOptionPane.showMessageDialog(frame, "User created successfully.", "User Creation", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showWeather() {
        String city = cityField.getText();
        String weather = weatherService.getWeather(city);
        weatherArea.setText(weather);
    }
}

// import Projects.Project_1_Heathly4You_demo.domain.User;
// import Projects.Project_1_Heathly4You_demo.service.WeatherService;
// import javax.swing.*;
// import java.awt.event.ActionEvent;

// public class DashboardUI {
//     private User loggedInUser;
//     private WeatherService weatherService;
//     private JFrame frame;

//     public DashboardUI(User user, WeatherService weatherService) {
//         this.loggedInUser = user;
//         this.weatherService = weatherService;
//         initializeUI();
//     }

//     private void initializeUI() {
//         frame = new JFrame("Healthy4You - Dashboard");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setSize(400, 300);
//         frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

//         JLabel welcomeLabel = new JLabel("Welcome, " + loggedInUser.getUsername());
//         JButton checkWeatherButton = new JButton("Check Weather");

//         checkWeatherButton.addActionListener(this::checkWeatherAction);

//         frame.add(welcomeLabel);
//         frame.add(checkWeatherButton);

//         // Additional UI components for user activities

//         frame.setVisible(true);
//     }

//     private void checkWeatherAction(ActionEvent e) {
//         // Call weatherService to get weather data
//         // Display the weather information
//     }

//     // Additional methods as needed
// }
