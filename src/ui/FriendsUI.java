package ui;

import DAOs.UserService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FriendsUI extends JPanel implements ActionListener {
    private DashboardUI dashboardUI;
    private JPanel friendsPanel;
    private JButton toggleButton, addButton;
    private boolean isVisible;
    private JScrollPane scrollPane;
    private List<String> friendsList;
    private UserService userService;

    // UI styling similar to SignUpUI
    private Font mediumfont = new Font("Monospaced", Font.BOLD, 16);
    private Color textcolor = new Color(156, 156, 156);
    private Color bgcolor = new Color(41, 41, 41);
    private Color buttoncolor = new Color(143, 88, 178);

    public FriendsUI(DashboardUI dashboardUI) {
        this.dashboardUI = dashboardUI;
        this.friendsList = new ArrayList<>();
        this.userService = new UserService();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 0));
        setBackground(bgcolor);

        // Friends panel
        friendsPanel = new JPanel();
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        friendsPanel.setBackground(bgcolor);

        // Scroll pane for friends panel
        scrollPane = new JScrollPane(friendsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBackground(bgcolor);

        // Toggle button
        toggleButton = new JButton(">");
        toggleButton.setFont(mediumfont);
        toggleButton.setForeground(textcolor);
        toggleButton.setBackground(bgcolor);
        toggleButton.addActionListener(this);

        // Add friend button
        addButton = new JButton("Add");
        addButton.setFont(mediumfont);
        addButton.setForeground(textcolor);
        addButton.setBackground(buttoncolor);
        addButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(toggleButton);
        buttonPanel.add(addButton);
        buttonPanel.setBackground(bgcolor);

        add(buttonPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // Initially hide the friends panel and add button
        toggleVisibility(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toggleButton) {
            toggleVisibility(!isVisible);
        } else if (e.getSource() == addButton) {
            String friendName = JOptionPane.showInputDialog(this, "Enter friend's username:");
            if (friendName != null && !friendName.isEmpty()) {
                try {
                    addFriend(friendName);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private void toggleVisibility(boolean visible) {
        isVisible = visible;
        scrollPane.setVisible(visible);
        addButton.setVisible(visible);
        toggleButton.setText(visible ? "<" : ">");
        revalidate();
        repaint();
    }

    private void addFriend(String friendName) throws IOException {

        if (userService.userExists(friendName) && !friendsList.contains(friendName)) {
            friendsList.add(friendName);
            JLabel friendLabel = new JLabel(friendName);
            friendLabel.setForeground(textcolor);
            friendsPanel.add(friendLabel);
            friendsPanel.revalidate();
            friendsPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or already a friend.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DashboardUI getDashboardUI() {
        return dashboardUI;
    }

    // Additional methods as needed...
}
