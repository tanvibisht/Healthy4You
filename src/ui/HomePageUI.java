package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;

public class HomePageUI implements ActionListener{

    JFrame frame = new JFrame("Healthy4You Homepage");
    JButton myButton = new JButton("Let's start");
    JPanel bottomPanel = new JPanel();

    public HomePageUI(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1050); // Set the dimensions to simulate a phone screen
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(32, 32, 32)); // Dark gray background

        myButton.setPreferredSize(new Dimension(500, 60)); // Make the button longer
        myButton.setFont(new Font("Arial", Font.PLAIN, 26)); // Set the button font
        myButton.setBackground(new Color(0,76,239)); // Dark red background color
        myButton.setForeground(Color.WHITE); // Text color
        myButton.setFocusPainted(false); // Remove the focus ring
        myButton.setOpaque(true); // Apply background color
        myButton.setBorderPainted(false); // Remove the border if you don't want it visible
        myButton.addActionListener(this);

        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20)); // Center the button with some padding at the bottom
        bottomPanel.setBackground(new Color(32, 32, 32)); // Match the frame's background
        bottomPanel.add(myButton);

        frame.add(bottomPanel, BorderLayout.SOUTH); // Add the panel to the bottom of the frame

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==myButton) {
            frame.dispose();
            DashboardUI dashboardUI = new DashboardUI();
        }
    }
}