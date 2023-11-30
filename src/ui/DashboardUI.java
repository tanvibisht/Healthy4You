package ui;

import org.json.JSONException;
import org.json.JSONObject;
import service.GeoLocationService;
import service.WeatherService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

class RoundedButton extends JButton {

    public RoundedButton() {
        super();
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        // corner radius
        int cornerRadius = 25;
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        super.paintComponent(g);
        g2.dispose();
    }
}

public class DashboardUI implements ActionListener {
    JFrame frame;
    private JPanel panel;
    private RoundedButton addActivityButton;
    private final Color backgroundColor = new Color(245, 245, 245); // Dark grey theme
    private final Color themeColor = new Color(84, 121, 247); // Blue theme color for buttons and panels

    private WeatherService weatherService;
    private GeoLocationService geoLocationService;
    private JLabel weatherLabel;

    public DashboardUI() throws MalformedURLException, JSONException {
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

        addActivityButton = new RoundedButton();
        addActivityButton.setPreferredSize(new Dimension(500, 60)); // Make the button longer
        addActivityButton.setBackground(themeColor);
        addActivityButton.setIcon(imageIcon);
        addActivityButton.setFocusPainted(false);
        addActivityButton.setBorderPainted(false);
        addActivityButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.add(addActivityButton);

        JPanel topPanel = new JPanel(new BorderLayout()); // Use BorderLayout
        weatherService = new WeatherService();
        geoLocationService = new GeoLocationService();
        weatherLabel = new JLabel("Loading weather...", SwingConstants.CENTER); // Set text alignment to center
        weatherLabel.setForeground(Color.WHITE);
        topPanel.setPreferredSize(new Dimension(600, 80));
        topPanel.setBackground(themeColor);
        topPanel.add(weatherLabel, BorderLayout.CENTER); // Add weatherLabel to the center of topPanel

        displayWeatherInfo();

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

    private void displayWeatherInfo() throws MalformedURLException, JSONException {

        String location = geoLocationService.getCity();

        if (!location.equals("Error fetching location")) {
            String city = location;
            String weatherData = weatherService.getWeather(city);
            JSONObject jsonObj = new JSONObject(weatherData);

            // Extracting the icon code
            String iconCode = jsonObj.getJSONArray("weather").getJSONObject(0).getString("icon");
            String iconUrl = "http://openweathermap.org/img/wn/" + iconCode + ".png";

            // Download and set the icon to a JLabel
            ImageIcon weatherIcon = new ImageIcon(new URL(iconUrl));
            weatherLabel.setIcon(weatherIcon);

            // Extracting temperature (assuming it's provided in Kelvin)
            double temperatureKelvin = jsonObj.getJSONObject("main").getDouble("temp");
            double temperatureCelsius = temperatureKelvin - 273.15; // Convert to Celsius

            // Extracting main weather condition
            String weatherCondition = jsonObj.getJSONArray("weather").getJSONObject(0).getString("main");

            weatherLabel.setText("Temp in " + city + ": " + String.format("%.2f", temperatureCelsius) + " °C, " + weatherCondition);

        } else {
            weatherLabel.setText("Unable to fetch location and weather data");
        }

        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new DashboardUI();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
