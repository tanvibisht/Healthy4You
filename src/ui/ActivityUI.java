package ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivityUI extends JFrame implements ActionListener {
    private DashboardUI dashboardUI;
    private JTextField activityNameField;
    private JTextArea activityDescriptionArea;
    private JButton createButton;

    public ActivityUI(DashboardUI dashboardUI) {
        super("Add New Activity");
        this.dashboardUI = dashboardUI;

        setLayout(new BorderLayout());
        setSize(300, 200);
        setLocationRelativeTo(dashboardUI.frame);

        activityNameField = new JTextField();
        activityDescriptionArea = new JTextArea();
        createButton = new JButton("Create");
        createButton.addActionListener(this);

        add(activityNameField, BorderLayout.NORTH);
        add(new JScrollPane(activityDescriptionArea), BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            String activityName = activityNameField.getText().trim();
            String activityDescription = activityDescriptionArea.getText().trim();

            if (!activityName.isEmpty() && !activityDescription.isEmpty()) {
                dashboardUI.addActivityPanel(activityName, activityDescription);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
