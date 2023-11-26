package ui;

import service.WeatherService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardUI implements ActionListener {
    JFrame frame;
    private JPanel panel;
    private JButton addActivityButton;
    private final Color backgroundColor = new Color(32, 32, 32); // Dark grey theme
    private final Color themeColor = new Color(0, 76, 239); // Blue theme color for buttons and panels

//    private WeatherService weatherService;
//    private JLabel weatherLabel;

    public DashboardUI() {
        ImageIcon originalImage = new ImageIcon("/Users/cristianoafonsodasilva/Desktop/University of Toronto/2023 Fall/CSC207/src/resource/plus.png");
        Image image = originalImage.getImage(); // Transform it
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // Scale it the smooth way
        ImageIcon imageIcon = new ImageIcon(newimg);  // Transform it back

        frame = new JFrame("Healthy4You Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1050);
        frame.setResizable(false);
        frame.getContentPane().setBackground(backgroundColor);

        // Main panel with FlowLayout to allow for fixed size panels
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Only vertical scrolling
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(scrollPane, BorderLayout.CENTER);


        addActivityButton = new JButton();
        addActivityButton.setPreferredSize(new Dimension(500, 60)); // Make the button longer
        addActivityButton.setBackground(themeColor);
        addActivityButton.setIcon(imageIcon);
        addActivityButton.setFocusPainted(false);
        addActivityButton.setOpaque(true);
        addActivityButton.setBorderPainted(false);
        addActivityButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.add(addActivityButton);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        weatherService = new WeatherService();
//        weatherLabel = new JLabel("Loading weather...");
//        weatherLabel.setForeground(Color.WHITE);
//        weatherLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        topPanel.setPreferredSize(new Dimension(600, 80));
        topPanel.setBackground(themeColor);
//        topPanel.add(weatherLabel);

//        displayWeatherInfo();

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(topPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addActivityButton) {
            new ActivityUI(this);
        }
    }

    // Method to add a new activity panel with a fixed size and blue theme
    public void addActivityPanel(String name, String description) {
        JPanel activityPanel = new JPanel();
        activityPanel.setPreferredSize(new Dimension(550, 60)); // Fixed size for activity panels
        activityPanel.setBackground(themeColor);
        activityPanel.setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel(name);
        nameLabel.setForeground(Color.white);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setForeground(Color.white);
        descriptionArea.setBackground(themeColor);
        descriptionArea.setEditable(false);

        activityPanel.add(nameLabel, BorderLayout.NORTH);
        activityPanel.add(descriptionArea, BorderLayout.CENTER);

        // Wrap the activity panel in another panel to maintain its size
        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
        wrapperPanel.setBackground(backgroundColor);
        wrapperPanel.add(activityPanel);

        panel.add(wrapperPanel);
        panel.revalidate();
        panel.repaint();
    }

//    private void displayWeatherInfo() {
//        // You can specify the city or make it dynamic as per your requirement
//        String city = "Toronto"; // Example city
//        String weatherData = weatherService.getWeather(city);
//
//        // Parse the weatherData to extract relevant information
//        // For simplicity, let's just display the raw JSON response
//        // You should parse this JSON to extract and format temperature, condition, etc.
//        weatherLabel.setText("Weather in " + city + ": " + weatherData);
//
//        // Repaint or revalidate if needed
//        frame.repaint();
//    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardUI());
    }
}
