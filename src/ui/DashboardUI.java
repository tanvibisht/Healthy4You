package ui;
import DAOs.UserDAO;
import Hydration.Hydration;
import Sleep.Sleep;
import Sleep.SleepUI;
import Hydration.HydrationGraphUI;
import domain.User;
import service.UserService;
import Recipe.RecipeGenerator;
import Recipe.RecipeUI;
import org.json.JSONException;
import org.json.JSONObject;
import service.GeoLocationService;
import service.UserService;
import service.WeatherService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class DashboardUI implements ActionListener {
    JFrame frame;
    private JPanel mainpanel;
    private JScrollPane scrollPane;
    private JPanel toppanel;
    private JPanel headpanel;
    private JPanel placeholder;
    private JButton logoutbutton;
    private JLabel iconlabel;
    private JLabel welcomelabel;
    private WeatherService weatherService;
    private JLabel weatherLabel;
    private JPanel buttonpanel;
    private JButton getRecipeButton;
    private JButton hydrationButton;

    private JButton sleepButton;
    private JPanel activitypanel;
    private JPanel activitytitlepanel;
    private JLabel activitytitlelabel;
    private JButton addActivityButton;

    private String username;
    private Color bgcolor = new Color(41, 41, 41);
    private Color themecolor = new Color(143, 88, 178);
    private Color headingcolor = new Color(255, 255, 255);
    private Color textcolor = new Color(156, 156, 156);
    private Color boxcolor = new Color(100, 80, 150);
    private Font largefont = new Font("Monospaced", Font.BOLD, 30);
    private Font mediumfont = new Font("Monospaced", Font.BOLD, 16);
    private Font smallfont = new Font("Monospaced", Font.BOLD, 12);
    private JTextField hourField, minuteField;
    private static final String SLEEP_FILE_PATH = "src/sleep.txt";

    private UserService userService;
    private UserDAO userDAO;
    private Hydration hydration;

    public DashboardUI(String username, UserService userService) throws MalformedURLException, JSONException {
        //frame setup

        this.username = username;
        this.userService = userService;
        this.userDAO = new UserDAO();
        this.hydration = new Hydration();

        frame = new JFrame();
        frame.setTitle("Healthy4You Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(530, 1100);
        frame.setBackground(bgcolor);

        //-----------------------------main panel-----------------------------

        //mainpanel setup
        mainpanel = new JPanel();
        mainpanel.setBackground(bgcolor);
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));

        //-----------------------------top panel-----------------------------

        //toppanel setup
        toppanel = new JPanel();
        toppanel.setPreferredSize(new Dimension(530, 350));
        toppanel.setMaximumSize(toppanel.getPreferredSize());
        toppanel.setMinimumSize(toppanel.getPreferredSize());
        toppanel.setBackground(bgcolor);
        toppanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        toppanel.setLayout(new BoxLayout(toppanel, BoxLayout.Y_AXIS));

        // Panel for logoutbutton and weatherLabel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(470, 40));
        headerPanel.setMaximumSize(headerPanel.getPreferredSize());
        headerPanel.setMinimumSize(headerPanel.getPreferredSize());
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.setBackground(bgcolor);

        // Configure logoutbutton
        logoutbutton = new JButton("Logout");
        logoutbutton.setFont(smallfont);
        logoutbutton.setForeground(textcolor);
        logoutbutton.setBackground(bgcolor);
        logoutbutton.setBorder(BorderFactory.createLineBorder(textcolor));
        logoutbutton.setFocusPainted(true);
        logoutbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginUI();
            }
        });

        // Configure weatherLabel
        weatherService = new WeatherService();
        weatherLabel = new JLabel("Loading weather data...", SwingConstants.CENTER);
        displayWeatherInfo(username, userService);
        weatherLabel.setFont(smallfont);
        weatherLabel.setForeground(headingcolor);
        weatherLabel.setHorizontalAlignment(SwingConstants.CENTER);
        weatherLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        placeholder = new JPanel();
        placeholder.setPreferredSize(logoutbutton.getPreferredSize());
        placeholder.setOpaque(false);

        // Add components to headerPanel
        headerPanel.add(logoutbutton, BorderLayout.WEST);
        headerPanel.add(weatherLabel, BorderLayout.CENTER);
        headerPanel.add(placeholder, BorderLayout.EAST);

        //imageIcon setup
        ImageIcon originalImage = new ImageIcon("/Users/cristianoafonsodasilva/Desktop/University of Toronto/2023 Fall/Healthy4You/version_8/src/resource/personicon.png");
        Image image = originalImage.getImage(); // Transform it
        Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(newimg);  // Transform it back

        //iconlabel setup
        iconlabel = new JLabel(imageIcon);
        iconlabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        //welcomelabel setup
        welcomelabel = new JLabel("Welcome " + username);
        welcomelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomelabel.setFont(largefont);
        welcomelabel.setForeground(headingcolor);

        //toppanel component
        toppanel.add(Box.createVerticalStrut(20));
        toppanel.add(headerPanel);
        toppanel.add(Box.createVerticalGlue());
        toppanel.add(iconlabel);
        toppanel.add((Box.createVerticalStrut(5)));
        toppanel.add(welcomelabel);
        toppanel.add(Box.createVerticalStrut(20));

        //-----------------------------button panel-----------------------------

        //buttonpanel setup
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonpanel.setPreferredSize(new Dimension(530, 50));
        buttonpanel.setMaximumSize(buttonpanel.getPreferredSize());
        buttonpanel.setMinimumSize(buttonpanel.getPreferredSize());
        buttonpanel.setBackground(bgcolor);
        buttonpanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        getRecipeButton = new JButton("Recipe");
        getRecipeButton.setMaximumSize(new Dimension(220, 50));
        getRecipeButton.setPreferredSize(new Dimension(220, 50));
        getRecipeButton.setFont(smallfont);
        getRecipeButton.setForeground(headingcolor);
        getRecipeButton.setBackground(boxcolor);
        getRecipeButton.setFocusPainted(false);
        getRecipeButton.setBorderPainted(false);
        getRecipeButton.setOpaque(true);
        getRecipeButton.setContentAreaFilled(true);
        getRecipeButton.addActionListener(this);

        hydrationButton = new JButton("Hydration");
        hydrationButton.setMaximumSize(new Dimension(220, 50));
        hydrationButton.setPreferredSize(new Dimension(220, 50));
        hydrationButton.setFont(smallfont);
        hydrationButton.setForeground(headingcolor);
        hydrationButton.setBackground(boxcolor);
        hydrationButton.setFocusPainted(false);
        hydrationButton.setBorderPainted(false);
        hydrationButton.setOpaque(true);
        hydrationButton.setContentAreaFilled(true);
        hydrationButton.addActionListener(this);
        sleepButton = new JButton("Sleep");
        sleepButton.setMaximumSize(new Dimension(220, 50));
        sleepButton.setPreferredSize(new Dimension(220, 50));

        sleepButton.setFont(smallfont);
        sleepButton.setForeground(headingcolor);
        sleepButton.setBackground(boxcolor);
        sleepButton.setFocusPainted(false);
        sleepButton.setBorderPainted(false);
        sleepButton.setOpaque(true);
        sleepButton.setContentAreaFilled(true);
        sleepButton.addActionListener(this); // Register the current instance as the action listener
        buttonpanel.add(sleepButton);
        buttonpanel.add(getRecipeButton);
        buttonpanel.add(hydrationButton);





        //-----------------------------activity panel-----------------------------

        //activity panel setup
        activitypanel = new JPanel();
        activitypanel.setLayout(new BoxLayout(activitypanel, BoxLayout.Y_AXIS));
        activitypanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        activitypanel.setBackground(bgcolor);

        // activity title panel setup
        activitytitlepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        activitytitlepanel.setPreferredSize(new Dimension(470, 30));
        activitytitlepanel.setMaximumSize(activitytitlepanel.getPreferredSize());
        activitytitlepanel.setMinimumSize(activitytitlepanel.getPreferredSize());
        activitytitlepanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        activitytitlepanel.setBackground(bgcolor);

        // activity title label setup
        activitytitlelabel = new JLabel("Habit Tracker");
        activitytitlelabel.setFont(mediumfont); // Ensure mediumfont is defined
        activitytitlelabel.setForeground(headingcolor);

        // activity title panel component
        activitytitlepanel.add(Box.createVerticalGlue());
        activitytitlepanel.add(activitytitlelabel);
        activitytitlepanel.add(Box.createVerticalGlue());

        //addActivityButton setup
        addActivityButton = new JButton("Add");
        addActivityButton.setMaximumSize(new Dimension(470, 50));
        addActivityButton.setPreferredSize(new Dimension(470, 50));
        addActivityButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addActivityButton.setFont(smallfont);
        addActivityButton.setForeground(headingcolor);
        addActivityButton.setBackground(boxcolor);
        addActivityButton.setFocusPainted(false);
        addActivityButton.setBorderPainted(false);
        addActivityButton.setOpaque(true);
        addActivityButton.setContentAreaFilled(true);
        addActivityButton.addActionListener(this);

        //activitypanel setup
        activitypanel.add(Box.createVerticalStrut(20));
        activitypanel.add(activitytitlepanel);
        activitypanel.add(Box.createVerticalStrut(20));
        activitypanel.add(addActivityButton);

        //mainpanel component
        mainpanel.add(toppanel);
        mainpanel.add(buttonpanel);
        mainpanel.add(activitypanel);
        mainpanel.add(Box.createVerticalStrut(20));

        // Scroll Pane for the Main Panel
        scrollPane = new JScrollPane(mainpanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null); // Remove the border
        frame.setContentPane(scrollPane);

        //frame component and display
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addActivityButton) {
            new ActivityUI(this);
        } else if (e.getSource() == getRecipeButton) { // Handle recipe button click
            // Generate a new recipe here using the RecipeGenerator
            RecipeGenerator recipeGenerator = new RecipeGenerator();
            String newRecipe = null;
            newRecipe = recipeGenerator.generateRandomRecipe();

            // Display the new recipe in a dialog or on the UI
            new RecipeUI(newRecipe);

        }
        else if (e.getSource() == hydrationButton) {
            new HydrationGraphUI(hydration); // Show the hydration gr // Show hydration window for the current user

        }
        else if (e.getSource() == sleepButton) {
            showSleepGraph();
        }
    }

    private void writeSleepData(String username) {
        String hours = hourField.getText();
        String minutes = minuteField.getText();

        try (FileWriter fw = new FileWriter(SLEEP_FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(username + ": " + hours + " hours " + minutes + " minutes");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error writing to file: " + ex.getMessage());
        }
    }

    private void clearSleepData() {
        hourField.setText("");
        minuteField.setText("");
    }

    public void removeTopActivity() {
        // Check if there are any activities in the panel
        if (activitypanel.getComponentCount() > 0) {
            // Assuming each activity is preceded by a vertical strut, remove it first
            activitypanel.remove(0);

            // Now remove the activity panel itself
            activitypanel.remove(0);

            // Refresh the panel to update the UI
            activitypanel.revalidate();
            activitypanel.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "No activities to remove.");
        }
    }

    private void showHydrationWindow(String username) {
        JDialog hydrationDialog = new JDialog(frame, "Hydration Tracker", true);
        hydrationDialog.setLayout(new FlowLayout());
        Hydration hydration = new Hydration();

        JTextField waterInputField = new JTextField(5);
        JButton addButton = new JButton("Add");
        JButton clearButton = new JButton("Clear Data"); // Clear Data button
        JLabel totalLabel = new JLabel("Total Liters: " + hydration.getTotalLiters(username));

        addButton.addActionListener(e -> {
            try {
                double liters = Double.parseDouble(waterInputField.getText());
                hydration.addWater(username, liters);
                totalLabel.setText("Total Liters: " + hydration.getTotalLiters(username));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(hydrationDialog, "Please enter a valid number.");
            }
        });

        clearButton.addActionListener(e -> {
            hydration.clearData(username); // Clear hydration data for the user
            totalLabel.setText("Total Liters: 0.0");
        });

        hydrationDialog.add(new JLabel("Enter water amount (liters):"));
        hydrationDialog.add(waterInputField);
        hydrationDialog.add(addButton);
        hydrationDialog.add(clearButton); // Add the Clear Data button
        hydrationDialog.add(totalLabel);

        hydrationDialog.pack();
        hydrationDialog.setLocationRelativeTo(frame);
        hydrationDialog.setVisible(true);
    }

    // Method to add a new activity panel with a fixed size and blue theme
    public void addActivityPanel(String description, String duration, String Completion, String time) {
        // Container panel for the subactivity panel and the vertical strut
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        containerPanel.setOpaque(false); // Set to false if you don't want this panel to have a background color

        // Subactivity panel setup
        JPanel subactivitypanel = new JPanel();
        subactivitypanel.setPreferredSize(new Dimension(470, 100));
        subactivitypanel.setMaximumSize(subactivitypanel.getPreferredSize());
        subactivitypanel.setMinimumSize(subactivitypanel.getPreferredSize());
        subactivitypanel.setBackground(themecolor);
        subactivitypanel.setLayout(new BorderLayout());

        // Name label setup
        JLabel nameLabel = new JLabel(time);
        nameLabel.setForeground(headingcolor);
        nameLabel.setFont(smallfont);

        // Description area setup
        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setForeground(Color.white);
        descriptionArea.setBackground(themecolor);
        descriptionArea.setEditable(false);

        // Delete button setup
        JButton deleteSubActivityButton = new JButton("X");
        deleteSubActivityButton.addActionListener(e -> {
            activitypanel.remove(containerPanel);
            activitypanel.revalidate();
            activitypanel.repaint();
        });

        // Panel for the name label and delete button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(nameLabel, BorderLayout.CENTER);
        headerPanel.add(deleteSubActivityButton, BorderLayout.EAST);
        headerPanel.setOpaque(false); // Make the panel transparent

        // Adding components to the subactivity panel
        subactivitypanel.add(headerPanel, BorderLayout.NORTH);
        subactivitypanel.add(descriptionArea, BorderLayout.CENTER);

        // Adding the subactivity panel and a vertical strut to the container panel
        containerPanel.add(Box.createVerticalStrut(20));
        containerPanel.add(subactivitypanel);

        // Adding the container panel to the activity panel
        activitypanel.add(containerPanel);
        activitypanel.revalidate();
        activitypanel.repaint();
    }
    private void showHydrationGraph() {
        HydrationGraphUI hydrationGraphUI = new HydrationGraphUI(hydration);
    }


    private void displayWeatherInfo(String username, UserService userService) throws JSONException {
        try {
            String location = userService.getUserLocation(username);
            String weatherData = weatherService.getWeather(location);

            // Log the response for debugging
            System.out.println("Weather data response: " + weatherData);

            // Check if the response is not JSON (does not start with '{')
            if (!weatherData.trim().startsWith("{")) {
                JOptionPane.showMessageDialog(frame, "Invalid response received from the server", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JSONObject jsonObj = new JSONObject(weatherData);
            if (!location.equals("Error fetching location")) {

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

                weatherLabel.setText(String.format("%d", (int) temperatureCelsius) + " °C in " + location + ", " + weatherCondition);

            } else {
                weatherLabel.setText("Unable to fetch location and weather data");
            }

            // Rest of your existing code for processing the JSON

        } catch (JSONException e) {
            JOptionPane.showMessageDialog(frame, "Invalid location or error parsing weather data", "JSON Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        frame.repaint();
    }

    private void showSleepGraph() {
        // Assuming SleepGraphUI takes a Sleep instance which reads data from sleep.txt
        Sleep sleepService = new Sleep(); // You need to implement the Sleep class
        SleepUI sleepGraphUI = new SleepUI(sleepService); // You need to implement the SleepGraphUI class

    public JPanel getActivitypanel() {
        return activitypanel;
    }
}
