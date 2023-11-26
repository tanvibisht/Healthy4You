package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.net.MalformedURLException;

class RoundedlabelButton extends JButton {

    public RoundedlabelButton(String label) {
        super(label);
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

public class HomePageUI implements ActionListener{

    JFrame frame = new JFrame("Healthy4You Homepage");
    JButton myButton = new RoundedlabelButton("Let's start");
    JPanel bottomPanel = new JPanel();
    JLabel logoLabel = new JLabel();

    public HomePageUI(){
//        ImageIcon originalImage = new ImageIcon("/Users/cristianoafonsodasilva/Desktop/University of Toronto/2023 Fall/CSC207/src/resource/logo.png");
//        Image image = originalImage.getImage(); // Transform it
//        Image newimg = image.getScaledInstance(400, 400,  java.awt.Image.SCALE_SMOOTH); // Scale it the smooth way
//        ImageIcon imageIcon = new ImageIcon(newimg);  // Transform it back

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1050); // Set the dimensions to simulate a phone screen
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(32, 32, 32)); // Dark gray background

        myButton.setPreferredSize(new Dimension(500, 60));
        myButton.setFont(new Font("Arial", Font.PLAIN, 26));
        myButton.setBackground(new Color(0,76,239));
        myButton.setForeground(Color.WHITE);
        myButton.setFocusPainted(false);
        myButton.setBorderPainted(false); // Remove the border if you don't want it visible
        myButton.addActionListener(this);

//        logoLabel.setIcon(imageIcon);

        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20)); // Center the button with some padding at the bottom
        bottomPanel.setBackground(new Color(32, 32, 32)); // Match the frame's background
        bottomPanel.add(myButton);

//        frame.add(logoLabel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH); // Add the panel to the bottom of the frame

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==myButton) {
            frame.dispose();
            try {
                DashboardUI dashboardUI = new DashboardUI();
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
