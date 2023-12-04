package Recipe;

import Hydration.Hydration;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import service.UserService;
import ui.DashboardUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeUI {
    private JFrame frame;
    private JPanel mainpanel;
    private String username;
    private UserService userService;
    private Color bgcolor = new Color(41, 41, 41);
    private Color themecolor = new Color(143, 88, 178);
    private Color headingcolor = new Color(255, 255, 255);
    private Color textcolor = new Color(156, 156, 156);
    private Color boxcolor = new Color(100, 80, 150);
    private Font largefont = new Font("Monospaced", Font.BOLD, 30);
    private Font mediumfont = new Font("Monospaced", Font.BOLD, 16);
    private Font smallfont = new Font("Monospaced", Font.BOLD, 12);
    public RecipeUI(String recipeText, String username, UserService userService) {
        this.username = username;
        this.userService = userService;

        if (recipeText == null || recipeText.isEmpty()) {
            recipeText = "No recipe found.";
        }

        frame = new JFrame("Healthy4You Recipe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(530, 1100);
        frame.setBackground(bgcolor);

        mainpanel = new JPanel();
        mainpanel.setBackground(bgcolor);
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));

        JTextArea recipeTextArea = new JTextArea(recipeText);
        recipeTextArea.setFont(smallfont);
        recipeTextArea.setBackground(bgcolor);
        recipeTextArea.setForeground(headingcolor);
        recipeTextArea.setWrapStyleWord(true);
        recipeTextArea.setLineWrap(true);
        recipeTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(recipeTextArea);
        mainpanel.add(scrollPane);
        addControlPanel();
        mainpanel.add(Box.createVerticalStrut(20));

        //frame component and display
        frame.setContentPane(mainpanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(530, 250));
        controlPanel.setMaximumSize(controlPanel.getPreferredSize());
        controlPanel.setMinimumSize(controlPanel.getPreferredSize());
        controlPanel.setBackground(bgcolor);
        controlPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        JPanel subcontrolPanel = new JPanel();
        subcontrolPanel.setPreferredSize(new Dimension(530, 60));
        subcontrolPanel.setMaximumSize(subcontrolPanel.getPreferredSize());
        subcontrolPanel.setMinimumSize(subcontrolPanel.getPreferredSize());
        subcontrolPanel.setBackground(bgcolor);
        subcontrolPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subcontrolPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30,0));

        JButton backButton = new JButton("Back to Dashboard");
        backButton.setMaximumSize(new Dimension(470, 60));
        backButton.setPreferredSize(new Dimension(470, 60));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setFont(mediumfont);
        backButton.setForeground(boxcolor);
        backButton.setBackground(bgcolor);
        backButton.setBorder(BorderFactory.createLineBorder(boxcolor));
        backButton.setFocusPainted(true);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    new DashboardUI(username,userService);
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        controlPanel.add(Box.createVerticalGlue());
        controlPanel.add(subcontrolPanel);
        controlPanel.add(Box.createVerticalStrut(30));
        controlPanel.add(backButton);
        controlPanel.add(Box.createVerticalGlue());

        mainpanel.add(controlPanel);
    }
}


