package ui;

import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.awt.Color;

// RoundedLabelButton extends from JButton for creating a JButton with Rounded corner
class RoundedLabelButton extends JButton {

    public RoundedLabelButton(String label, Color bgColor) {
        super(label);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setBackground(bgColor);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 25, 25);
        super.paintComponent(g2);
        g2.dispose();
    }
}

public class HomePageUI implements ActionListener {

    private JFrame frame;
    private JButton startButton;
    private JPanel mainPanel;
    private JLabel logoLabel;
    private JLabel greetingLabel;
    private JLabel sloganLabel;

    public HomePageUI() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Healthy4You Homepage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1050);
        frame.setResizable(false);

        Color themeColor = new Color(84, 121, 247); // Color #5479f7

        greetingLabel = new JLabel("Hello!");
        greetingLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        greetingLabel.setForeground(themeColor);
        greetingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sloganLabel = new JLabel("It's time to make your life better");
        sloganLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        sloganLabel.setForeground(Color.GRAY);
        sloganLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        ImageIcon originalImage = new ImageIcon("/Users/cristianoafonsodasilva/Desktop/University of Toronto/2023 Fall/CSC207/src/resource/HomePage.png"); // Adjust the path
        Image image = originalImage.getImage();
        Image newimg = image.getScaledInstance(380, 380, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(newimg);

        logoLabel = new JLabel(imageIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton = new RoundedLabelButton("Let's start", themeColor);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setMaximumSize(new Dimension(340, 80));
        startButton.setFont(new Font("Arial", Font.PLAIN, 26));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.addActionListener(this);

        mainPanel.add(Box.createVerticalStrut(200));
        mainPanel.add(greetingLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(sloganLabel);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(startButton);
        mainPanel.add(Box.createVerticalGlue());

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            frame.dispose();
            try {
                DashboardUI dashboardUI = new DashboardUI();
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
