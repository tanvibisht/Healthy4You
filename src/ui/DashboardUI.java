package ui;
import DAOs.*;
import DAOs.ActivitiesDAO.ActivitySaver;
import Usecase.Hydration.Hydration;
import Usecase.Sleep.Sleep;
import Usecase.Activites.ShowActivityList.Interactor;
import service.Controllers.*;
import org.json.JSONException;
import org.json.JSONObject;
import ui.ActivityPresenter.*;
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
    public JFrame frame;
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
    public JButton getRecipeButton;
    private JButton hydrationButton;

    private JButton sleepButton;
    private JPanel activitypanel;
    private JPanel activitytitlepanel;
    private JLabel activitytitlelabel;
    private JPanel addActivityButtonpanel;
    private JButton addActivityButton;
    private JButton trackActivityButton;
    private UserService userService;
    private String username;
    private Color bgcolor = new Color(41, 41, 41);
    private Color themecolor = new Color(143, 88, 178);
    private Color headingcolor = new Color(255, 255, 255);
    private Color textcolor = new Color(156, 156, 156);
    private Color boxcolor = new Color(100, 80, 150);
    private Color pressedboxcolor = new Color(50,40,75);
    private Font largefont = new Font("Monospaced", Font.BOLD, 30);
    private Font mediumfont = new Font("Monospaced", Font.BOLD, 16);
    private Font smallfont = new Font("Monospaced", Font.BOLD, 12);
    private JTextField hourField, minuteField;
    private static final String SLEEP_FILE_PATH = "src/sleep.txt";

    private UserDAO userDAO;
    private Hydration hydration;

    private final ShowActivity showActivity;

    private final SaveActivity saveActivity;

    private Boolean track = false;

    public DashboardUI(String username, UserService userService) throws MalformedURLException, JSONException {
        this.username = username;
        this.userService = userService;
        this.userDAO = new UserDAO();
        this.hydration = new Hydration();

        SaveActivityPresenter saveActivityPresenter = new SaveActivityPresenter(this);
        ActivitySaver activitySaver = new ActivitySaver();
        Usecase.Activites.SaveActivities.Interactor saveActivityInteractor = (
                new Usecase.Activites.SaveActivities.Interactor(saveActivityPresenter, activitySaver));
        saveActivity = new SaveActivity(saveActivityInteractor);

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
        logoutbutton.setBorderPainted(false);
        logoutbutton.setFocusPainted(false);
        logoutbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveActivity.execute();
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
        Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
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
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        buttonpanel.setPreferredSize(new Dimension(520, 50));
        buttonpanel.setMaximumSize(buttonpanel.getPreferredSize());
        buttonpanel.setMinimumSize(buttonpanel.getPreferredSize());
        buttonpanel.setBackground(bgcolor);
        buttonpanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        getRecipeButton = new JButton("Recipe");
        getRecipeButton.setMaximumSize(new Dimension(140, 50));
        getRecipeButton.setPreferredSize(new Dimension(140, 50));
        getRecipeButton.setFont(smallfont);
        getRecipeButton.setForeground(headingcolor);
        getRecipeButton.setBackground(boxcolor);
        getRecipeButton.setFocusPainted(false);
        getRecipeButton.setBorderPainted(false);
        getRecipeButton.setOpaque(true);
        getRecipeButton.setContentAreaFilled(true);
        getRecipeButton.addActionListener(this);

        hydrationButton = new JButton("Hydration");
        hydrationButton.setMaximumSize(new Dimension(140, 50));
        hydrationButton.setPreferredSize(new Dimension(140, 50));
        hydrationButton.setFont(smallfont);
        hydrationButton.setForeground(headingcolor);
        hydrationButton.setBackground(boxcolor);
        hydrationButton.setFocusPainted(false);
        hydrationButton.setBorderPainted(false);
        hydrationButton.setOpaque(true);
        hydrationButton.setContentAreaFilled(true);
        hydrationButton.addActionListener(this);

        sleepButton = new JButton("Sleep");
        sleepButton.setMaximumSize(new Dimension(140, 50));
        sleepButton.setPreferredSize(new Dimension(140, 50));
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
        
        addActivityButtonpanel = new JPanel();
        addActivityButtonpanel.setPreferredSize(new Dimension(530, 50));
        addActivityButtonpanel.setMaximumSize(addActivityButtonpanel.getPreferredSize());
        addActivityButtonpanel.setMinimumSize(addActivityButtonpanel.getPreferredSize());
        addActivityButtonpanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addActivityButtonpanel.setBackground(bgcolor);
        addActivityButtonpanel.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));

        //addActivityButton setup
        addActivityButton = new JButton("Add");
        addActivityButton.setMaximumSize(new Dimension(220, 50));
        addActivityButton.setPreferredSize(new Dimension(220, 50));
        addActivityButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addActivityButton.setFont(smallfont);
        addActivityButton.setForeground(headingcolor);
        addActivityButton.setBackground(boxcolor);
        addActivityButton.setFocusPainted(false);
        addActivityButton.setBorderPainted(false);
        addActivityButton.setOpaque(true);
        addActivityButton.setContentAreaFilled(true);
        addActivityButton.addActionListener(this);

        //trackActivityButton setup
        trackActivityButton = new JButton("Track Activity");
        trackActivityButton.setMaximumSize(new Dimension(220, 50));
        trackActivityButton.setPreferredSize(new Dimension(220, 50));
        trackActivityButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        trackActivityButton.setFont(smallfont);
        trackActivityButton.setForeground(headingcolor);
        trackActivityButton.setBackground(boxcolor);
        trackActivityButton.setFocusPainted(false);
        trackActivityButton.setBorderPainted(false);
        trackActivityButton.setOpaque(true);
        trackActivityButton.setContentAreaFilled(true);
        trackActivityButton.addActionListener(this);

        addActivityButtonpanel.add(addActivityButton);
        addActivityButtonpanel.add(trackActivityButton);

        //mainpanel component
        mainpanel.add(toppanel);
        mainpanel.add(buttonpanel);
        mainpanel.add(Box.createVerticalStrut(20));
        mainpanel.add(activitytitlepanel);
        mainpanel.add(Box.createVerticalStrut(20));
        mainpanel.add(addActivityButtonpanel);
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

        ShowActivityListPresenter showActivityListPresenter = new ShowActivityListPresenter(this);
        Interactor showActivityInteractor = new Interactor(showActivityListPresenter);
        showActivity = new ShowActivity(showActivityInteractor);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addActivityButton) {
            frame.dispose();
            new ActivityUI(this);
        } else if (e.getSource() == getRecipeButton) { // Handle recipe button click
            // Generate a new recipe here using the RecipeGenerator
            RecipeGenerator recipeGenerator = new RecipeGenerator();
            String newRecipe = null;
            newRecipe = recipeGenerator.generateRandomRecipe();

            frame.dispose();
            new RecipeUI(newRecipe,this);

        } else if (e.getSource() == hydrationButton) {
            frame.dispose();
            new HydrationGraphUI(hydration, this); // Show the hydration gr // Show hydration window for the current user

        } else if (e.getSource() == sleepButton) {
            frame.dispose();
            Sleep sleepService = new Sleep(); // You need to implement the Usecase.Sleep class
            new SleepUI(sleepService, this); // You need to implement the SleepGraphUI class
        } else if (e.getSource() == trackActivityButton){
            if (!track) {
                TrackActivity trackActivity = TrackActivityFactory.build(this);
                trackActivity.execute();
                trackActivityButton.setBackground(pressedboxcolor);
                track = true;
            }else{
                showActivity.execute();
                trackActivityButton.setBackground(boxcolor);
                track = false;
            }
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

    // Method to add a new activity panel with a fixed size and blue theme
    public void addActivityPanel(String name, String duration, String completion, String description, String index) {
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
        JLabel nameLabel = new JLabel(name);
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
            String i  = index;
            DeleteActivityPresenter deleteActivityPresenter = new DeleteActivityPresenter(this);
            Usecase.Activites.DeleteActivity.Interactor deleteActivityInteractor = (
                    new Usecase.Activites.DeleteActivity.Interactor(deleteActivityPresenter));
            DeleteActivity deleteActivity = new DeleteActivity(deleteActivityInteractor);
            deleteActivity.execute(Integer.parseInt(i));
            showActivity.execute();
        });

        // Panel for the name label and delete button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(nameLabel, BorderLayout.CENTER);
        headerPanel.add(deleteSubActivityButton, BorderLayout.EAST);
        if (!Boolean.valueOf(completion)){
            JButton completeSubActivityButton = new JButton("complete");
            completeSubActivityButton.addActionListener(e -> {
                String i = index;
                CompleteActivityPresenter completeActivityPresenter = new CompleteActivityPresenter(this);
                Usecase.Activites.CompleteActivity.Interactor completeActivityInteractor = (
                        new Usecase.Activites.CompleteActivity.Interactor(completeActivityPresenter));
                CompleteActivity completeActivity = new CompleteActivity(completeActivityInteractor);
                completeActivity.execute(Integer.parseInt(index));
                showActivity.execute();
            });
            headerPanel.add(completeSubActivityButton, BorderLayout.NORTH);
        }
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

    public void displayWeatherInfo(String username, UserService userService) throws JSONException {
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

    public JPanel getActivitypanel() {
        return activitypanel;
    }

    public void showActivity(){
        showActivity.execute();
    }
    public void showFrame() {
        frame.setVisible(true);
    }
    public String getName() {
        return this.username;
    }


}
