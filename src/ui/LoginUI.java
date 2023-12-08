package ui;


import DAOs.ActivitiesDAO.ActivityReader;
import Usecase.Activites.UpdateDatabaseActivities.Interactor;
import domain.LoggedUser;
import domain.User;
import org.json.JSONException;
import service.Controllers.UpdateDatabaseActivities;
import DAOs.UserService;
import DAOs.UserDAO;
import ui.ActivityPresenter.UpdateActivityPresenter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LoginUI {
    private JFrame frame;
    private JPanel mainpanel;
    private JPanel toppanel;
    private JLabel headinglabel;
    private JPanel usernamepanel;
    private JLabel usernamelabel;
    private JPanel passwordpanel;
    private JLabel passwordlabel;
    private JPanel locationpanel;
    private JLabel locationlabel;
    private JTextField locationField;
    private JCheckBox locationcheckbox;
    private JButton backbutton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginbutton;
    private JButton signupbutton;
    private Font largefont = new Font("Monospaced", Font.BOLD, 30);
    private Font mediumfont = new Font("Monospaced", Font.BOLD, 16);
    private Font smallfont = new Font("Monospaced", Font.BOLD, 12);
    private Color bgcolor = new Color(41, 41, 41);
    private Color themecolor = new Color(143, 88, 178);
    private Color headingcolor = new Color(255, 255, 255);
    private Color textcolor = new Color(156, 156, 156);
    private Border line = BorderFactory.createLineBorder(textcolor);
    private Border margin = new EmptyBorder(5, 10, 5, 5);

    public LoginUI() {
        //frame setup
        frame = new JFrame();
        frame.setTitle("Healthy4You Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(530, 1100);
        frame.setBackground(bgcolor);

        //-----------------------------main panel-----------------------------
        //mainpanel setup
        mainpanel = new JPanel();
        mainpanel.setBackground(bgcolor);
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));

        //-----------------------------top panel-----------------------------

        // Panel for back button with left alignment
        toppanel = new JPanel();
        toppanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        toppanel.setMaximumSize(new Dimension(400, 150));
        toppanel.setBackground(bgcolor);
        toppanel.setLayout(new BoxLayout(toppanel, BoxLayout.Y_AXIS));

        // backbutton setup
        backbutton = new JButton("<");
        backbutton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backbutton.setHorizontalAlignment(SwingConstants.CENTER);
        backbutton.setPreferredSize(new Dimension(40, 40));
        backbutton.setMaximumSize(new Dimension(40, 40));
        backbutton.setFont(mediumfont);
        backbutton.setForeground(textcolor);
        backbutton.setBackground(bgcolor);
        backbutton.setBorder(BorderFactory.createLineBorder(textcolor));
        backbutton.setFocusPainted(true);
        backbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePageUI homePageUI = new HomePageUI();
            }
        });

        //loginlabel setup
        headinglabel = new JLabel("Login");
        headinglabel.setFont(largefont);
        headinglabel.setForeground(headingcolor);
        headinglabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //toppanel component
        toppanel.add(Box.createVerticalStrut(40));
        toppanel.add(backbutton);
        toppanel.add(Box.createVerticalStrut(30));
        toppanel.add(headinglabel);
        toppanel.add(Box.createVerticalGlue());

        //-----------------------------username panel-----------------------------

        //usernamepanel setup
        usernamepanel = new JPanel();
        usernamepanel.setMaximumSize(new Dimension(400, 100));
        usernamepanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamepanel.setBackground(bgcolor);
        usernamepanel.setLayout(new BoxLayout(usernamepanel, BoxLayout.Y_AXIS));

        //usernamelabel setup
        usernamelabel = new JLabel("Username");
        usernamelabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernamelabel.setFont(mediumfont);
        usernamelabel.setForeground(textcolor);

        //usernameField setup
        usernameField = new JTextField();
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameField.setMaximumSize(new Dimension(400, 60));
        usernameField.setPreferredSize(new Dimension(400, 60));
        usernameField.setFont(mediumfont);
        usernameField.setCaretColor(headingcolor);
        usernameField.setForeground(headingcolor);
        usernameField.setBackground(bgcolor);
        usernameField.setBorder(new CompoundBorder(line, margin));

        //usernamepanel component
        usernamepanel.add(Box.createVerticalGlue());
        usernamepanel.add(usernamelabel);
        usernamepanel.add(Box.createVerticalStrut(10));
        usernamepanel.add(usernameField);
        usernamepanel.add(Box.createVerticalGlue());

        //-----------------------------password panel-----------------------------

        //passwordpanel setup
        passwordpanel = new JPanel();
        passwordpanel.setMaximumSize(new Dimension(400, 100));
        passwordpanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordpanel.setBackground(bgcolor);
        passwordpanel.setLayout(new BoxLayout(passwordpanel, BoxLayout.Y_AXIS));

        //passwordlabel setup
        passwordlabel = new JLabel("Password");
        passwordlabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordlabel.setFont(mediumfont);
        passwordlabel.setForeground(textcolor);

        //passwordField setup
        passwordField = new JPasswordField();
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordField.setMaximumSize(new Dimension(400, 60));
        passwordField.setPreferredSize(new Dimension(400, 60));
        passwordField.setFont(mediumfont);
        passwordField.setCaretColor(headingcolor);
        passwordField.setForeground(headingcolor);
        passwordField.setBackground(bgcolor);
        passwordField.setBorder(new CompoundBorder(line, margin));

        //passwordpanel component
        passwordpanel.add(Box.createVerticalGlue());
        passwordpanel.add(passwordlabel);
        passwordpanel.add(Box.createVerticalStrut(10));
        passwordpanel.add(passwordField);
        passwordpanel.add(Box.createVerticalGlue());

        //-----------------------------location panel-----------------------------

        //locationpanel setup
        locationpanel = new JPanel();
        locationpanel.setMaximumSize(new Dimension(400, 100));
        locationpanel.setMaximumSize(new Dimension(400, 130));
        locationpanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        locationpanel.setBackground(bgcolor);
        locationpanel.setLayout(new BoxLayout(locationpanel, BoxLayout.Y_AXIS));

        //locationlabel setup
        locationlabel = new JLabel("Location");
        locationlabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        locationlabel.setFont(mediumfont);
        locationlabel.setForeground(textcolor);

        //locationField setup
        locationField = new JTextField();
        locationField.setAlignmentX(Component.LEFT_ALIGNMENT);
        locationField.setMaximumSize(new Dimension(400, 60));
        locationField.setPreferredSize(new Dimension(400, 60));
        locationField.setFont(mediumfont);
        locationField.setCaretColor(headingcolor);
        locationField.setForeground(headingcolor);
        locationField.setBackground(bgcolor);
        locationField.setBorder(new CompoundBorder(line, margin));

        //locationcheckbox setup
        locationcheckbox = new JCheckBox("Use previous location");
        locationcheckbox.setFont(smallfont);
        locationcheckbox.setForeground(textcolor);
        locationcheckbox.setAlignmentX(Component.LEFT_ALIGNMENT);
        locationcheckbox.addActionListener(e -> {
            try {
                addlocation();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        //locationpanel component
        locationpanel.add(Box.createVerticalGlue());
        locationpanel.add(locationlabel);
        locationpanel.add(Box.createVerticalStrut(10));
        locationpanel.add(locationField);
        locationpanel.add(Box.createVerticalStrut(10));
        locationpanel.add(locationcheckbox);
        locationpanel.add(Box.createVerticalGlue());

        //-----------------------------login button-----------------------------

        //loginbutton setup
        loginbutton = new JButton("Login");
        loginbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginbutton.setHorizontalAlignment(SwingConstants.CENTER);
        loginbutton.setMaximumSize(new Dimension(400, 60));
        loginbutton.setFont(mediumfont);
        loginbutton.setForeground(headingcolor);
        loginbutton.setBackground(themecolor);
        loginbutton.setOpaque(true);
        loginbutton.setBorderPainted(false);
        loginbutton.setFocusPainted(false);
        loginbutton.addActionListener(e -> performLogin());

        //-----------------------------signup button-----------------------------

        //signupbutton setup
        signupbutton = new JButton("Sign Up");
        signupbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupbutton.setHorizontalAlignment(SwingConstants.CENTER);
        signupbutton.setMaximumSize(new Dimension(400, 60));
        signupbutton.setFont(mediumfont);
        signupbutton.setForeground(themecolor);
        signupbutton.setBackground(bgcolor);
        signupbutton.setBorder(BorderFactory.createLineBorder(themecolor));
        signupbutton.setFocusPainted(true);
        signupbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SignUpUI signUpUI = new SignUpUI(frame);
            }
        });

        //mainpanel component
        mainpanel.add(Box.createVerticalStrut(30));
        mainpanel.add(toppanel);
        mainpanel.add(Box.createVerticalStrut(20));
        mainpanel.add(usernamepanel);
        mainpanel.add(Box.createVerticalStrut(20));
        mainpanel.add(passwordpanel);
        mainpanel.add(Box.createVerticalStrut(20));
        mainpanel.add(locationpanel);
        mainpanel.add(Box.createVerticalStrut(50));
        mainpanel.add(loginbutton);
        mainpanel.add(Box.createVerticalStrut(20));
        mainpanel.add(signupbutton);

        //frame component and display
        frame.setContentPane(mainpanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void performLogin() {
        String username = new String(usernameField.getText());
        String password = new String(passwordField.getPassword());
        String location = new String(locationField.getText());
        UserService userService = new UserService();
        UserDAO userDAO = new UserDAO();
        try {
            if (userService.validateLogin(username, password)) {
                if (!location.isEmpty()) {
                    userService.saveUserLocation(username, location);
                    saveCurrentUsername(username);
                } else {
                    JOptionPane.showMessageDialog(frame, "Location not provided.", "Weather Data Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                frame.dispose();
                DashboardUI ui = new DashboardUI(username, userService);
                LoggedUser.setUser(new User(username, password)); //Set for now, need more function
                UpdateActivityPresenter updateActivityPresenter = new UpdateActivityPresenter(ui);
                ActivityReader activityReader = new ActivityReader();
                Interactor updateActivityInteractor = new Interactor(updateActivityPresenter, activityReader);
                UpdateDatabaseActivities updateDatabaseActivities = new UpdateDatabaseActivities(
                        updateActivityInteractor);
                updateDatabaseActivities.execute();
                ui.showActivity();
                // Close the login window
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle exceptions properly in production code
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    private void saveCurrentUsername(String username) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("currentusername.txt"))) {
            writer.write(username);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving username: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void addlocation () throws IOException {
        String username = new String(usernameField.getText());
        UserService userService = new UserService();
        try {
            if (locationcheckbox.isSelected()) {
                // Set the locationtextfield to a default location when the checkbox is checked
                if (userService.userExists(username)) {
                    String location = userService.getUserLocation(username);
                    locationField.setText(location); // Replace with the actual location you want to set
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username", "Invalid username", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                // Optionally clear the textfield when the checkbox is unchecked
                locationField.setText("");
            }
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle exceptions properly in production code
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}