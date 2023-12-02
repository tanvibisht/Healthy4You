package ui;

import org.json.JSONException;
import service.UserService;
import service.WeatherService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginUI {
    private JFrame frame;
    private JPanel mainpanel;
    private JPanel toppanel;
    private JPanel usernamepanel;
    private JLabel usernamelabel;
    private JPanel passwordpanel;
    private JLabel passwordlabel;
    private JPanel locationpanel;
    private JLabel locationlabel;
    private JTextField locationField;
    private JButton backbutton;
    private JLabel headinglabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginbutton;
    private JButton signupbutton;
    private Font headingfont = new Font("Monospaced", Font.BOLD, 30);
    private Font buttonfont = new Font("SansSerif", Font.BOLD, 14);
    private Font textfont = new Font("SansSerif", Font.PLAIN, 16);
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
        backbutton.setFont(textfont);
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
        headinglabel.setFont(headingfont);
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
        usernamelabel.setFont(textfont);
        usernamelabel.setForeground(textcolor);

        //usernameField setup
        usernameField = new JTextField();
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameField.setMaximumSize(new Dimension(400, 60));
        usernameField.setPreferredSize(new Dimension(400, 60));
        usernameField.setFont(textfont);
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
        passwordlabel.setFont(textfont);
        passwordlabel.setForeground(textcolor);

        //passwordField setup
        passwordField = new JPasswordField();
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordField.setMaximumSize(new Dimension(400, 60));
        passwordField.setPreferredSize(new Dimension(400, 60));
        passwordField.setFont(textfont);
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
        locationpanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        locationpanel.setBackground(bgcolor);
        locationpanel.setLayout(new BoxLayout(locationpanel, BoxLayout.Y_AXIS));

        //locationlabel setup
        locationlabel = new JLabel("Location");
        locationlabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        locationlabel.setFont(textfont);
        locationlabel.setForeground(textcolor);

        //locationField setup
        locationField = new JTextField();
        locationField.setAlignmentX(Component.LEFT_ALIGNMENT);
        locationField.setMaximumSize(new Dimension(400, 60));
        locationField.setPreferredSize(new Dimension(400, 60));
        locationField.setFont(textfont);
        locationField.setCaretColor(headingcolor);
        locationField.setForeground(headingcolor);
        locationField.setBackground(bgcolor);
        locationField.setBorder(new CompoundBorder(line, margin));

        //locationpanel component
        locationpanel.add(Box.createVerticalGlue());
        locationpanel.add(locationlabel);
        locationpanel.add(Box.createVerticalStrut(10));
        locationpanel.add(locationField);
        locationpanel.add(Box.createVerticalGlue());

        //-----------------------------login button-----------------------------

        //loginbutton setup
        loginbutton = new JButton("Login");
        loginbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginbutton.setHorizontalAlignment(SwingConstants.CENTER);
        loginbutton.setMaximumSize(new Dimension(400, 60));
        loginbutton.setFont(buttonfont);
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
        signupbutton.setFont(buttonfont);
        signupbutton.setForeground(headingcolor);
        signupbutton.setBackground(bgcolor);
        signupbutton.setBorder(BorderFactory.createLineBorder(textcolor));
        signupbutton.setFocusPainted(true);
        signupbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SignUpUI signUpUI = new SignUpUI();
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
        try {
            if (userService.validateLogin(username, password)) {
                if (!location.isEmpty()) {
                    userService.saveUserLocation(username, location);
                } else {
                    JOptionPane.showMessageDialog(frame, "Location not provided.", "Weather Data Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                frame.dispose();
                new DashboardUI(username, userService);
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
}
