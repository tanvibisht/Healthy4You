package ui;

import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

public class HomePageUI {
    private JFrame frame;
    private JPanel mainpanel;
    private JPanel toppanel;
    private JLabel imagelabel;
    private JPanel centerpanel;
    private JLabel applicationnamelabel;
    private JLabel applicationsloganlabel;
    private JPanel bottompanel;
    private JButton loginbutton;
    private JButton signupbutton;
    private JLabel authorlabel;
    private Font largefont = new Font("Monospaced", Font.BOLD, 30);
    private Font mediumfont = new Font("Monospaced", Font.BOLD, 16);
    private Font smallfont = new Font("Monospaced", Font.BOLD, 12);
    private Color bgcolor = new Color(41, 41, 41);
    private Color themecolor = new Color(143, 88, 178);
    private Color headingcolor = new Color(255, 255, 255);
    private Color textcolor = new Color(156, 156, 156);

    public HomePageUI() {
        //frame setup
        frame = new JFrame();
        frame.setTitle("Healthy4You HomePage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(530, 1100);
        frame.setBackground(bgcolor);

        //-----------------------------main panel-----------------------------
        //mainpanel setup
        mainpanel = new JPanel();
        mainpanel.setBackground(bgcolor);
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));

        //-----------------------------top panel-----------------------------
        //imageIcon setup
        ImageIcon originalImage = new ImageIcon("/Users/cristianoafonsodasilva/Desktop/University of Toronto/2023 Fall/Healthy4You/version_7/src/resource/logo.png");
        Image image = originalImage.getImage(); // Transform it
        Image newimg = image.getScaledInstance(530, 600,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(newimg);  // Transform it back

        //imagelabel setup
        imagelabel = new JLabel(imageIcon);

        //toppanel setup
        toppanel = new JPanel();
        toppanel.setPreferredSize(new Dimension(530, 600));
        toppanel.add(imagelabel);
        toppanel.setBackground(bgcolor);

        //-----------------------------center panel-----------------------------
        //applicationnamelabel setup
        applicationnamelabel = new JLabel("Healthy4You");
        applicationnamelabel.setFont(largefont);
        applicationnamelabel.setForeground(headingcolor);
        applicationnamelabel.setHorizontalAlignment(SwingConstants.CENTER);
        applicationnamelabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //applicationsloganlabel setup
        applicationsloganlabel = new JLabel("Your Health Companion");
        applicationsloganlabel.setFont(smallfont);
        applicationsloganlabel.setForeground(textcolor);
        applicationsloganlabel.setHorizontalAlignment(SwingConstants.CENTER);
        applicationsloganlabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //centerpanel setup
        centerpanel = new JPanel();
        centerpanel.setPreferredSize(new Dimension(530, 100));
        centerpanel.setBackground(bgcolor);
        centerpanel.setLayout(new BoxLayout(centerpanel, BoxLayout.Y_AXIS));

        //centerpanel components
        centerpanel.add(Box.createVerticalGlue());
        centerpanel.add(applicationnamelabel);
        centerpanel.add(Box.createVerticalStrut(10));
        centerpanel.add(applicationsloganlabel);
        centerpanel.add(Box.createVerticalGlue());

        //-----------------------------bottom panel-----------------------------
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

        loginbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LoginUI loginUI = new LoginUI();
            }
        });

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
                SignUpUI signUpUI = new SignUpUI();
            }
        });

        //authorlabel setup
        authorlabel = new JLabel("created by Anything4GPA");
        authorlabel.setFont(smallfont);
        authorlabel.setForeground(textcolor);
        authorlabel.setHorizontalAlignment(SwingConstants.CENTER);
        authorlabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //bottompanel setup
        bottompanel = new JPanel();
        bottompanel.setPreferredSize(new Dimension(530, 400));
        bottompanel.setBackground(bgcolor);
        bottompanel.setLayout(new BoxLayout(bottompanel, BoxLayout.Y_AXIS));

        //bottompanel component
        bottompanel.add(Box.createVerticalStrut(30));
        bottompanel.add(loginbutton);
        bottompanel.add(Box.createVerticalStrut(20));
        bottompanel.add(signupbutton);
        bottompanel.add(Box.createVerticalStrut(130));
        bottompanel.add(authorlabel);

        //mainpanel component
        mainpanel.add(toppanel);
        mainpanel.add(centerpanel);
        mainpanel.add(bottompanel);

        //frame component and display
        frame.setContentPane(mainpanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Set Nimbus Look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }
    }
}
