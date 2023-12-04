package ui;

import Usecase.Activites.CreateActivity.CreatActivityInteractor;
import Usecase.Activites.ShowActivityList.Interactor;
import service.Controllers.CreateActivity;
import service.Controllers.ShowActivity;
import ui.ActivityPresenter.AddActivityPresenter;
import ui.ActivityPresenter.ShowActivityListPresenter;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivityUI extends Component implements ActionListener {
    private JFrame frame;
    private JPanel mainpanel;
    private JPanel toppanel;
    private JLabel headinglabel;
    private JPanel activityNamepanel;
    private JLabel activityNamelabel;
    private JTextField activityNameField;
    private JPanel activityTimeDurationpanel;
    private JLabel activityTimeDurationlabel;
    private JTextField activityTimeDurationField;
    private JPanel activityStartTimepanel;
    private JPanel subactivityStartTimepanel;
    private JLabel activityStartTimelabel;
    private JTextField yearField;
    private JTextField monthField;
    private JTextField dayField;
    private JTextField hourField;
    private JTextField minuteField;
    private DashboardUI dashboardUI;
    private JButton createButton;
    private  ShowActivity showActivity;
    private Color bgcolor = new Color(41, 41, 41);
    private Color themecolor = new Color(143, 88, 178);
    private Color headingcolor = new Color(255, 255, 255);
    private Color textcolor = new Color(156, 156, 156);
    private Color boxcolor = new Color(100, 80, 150);
    private Font largefont = new Font("Monospaced", Font.BOLD, 30);
    private Font mediumfont = new Font("Monospaced", Font.BOLD, 16);
    private Font smallfont = new Font("Monospaced", Font.BOLD, 12);
    private Border line = BorderFactory.createLineBorder(textcolor);
    private Border margin = new EmptyBorder(5, 10, 5, 5);

    public ActivityUI(DashboardUI dashboardUI) {
        this.dashboardUI = dashboardUI;

        ShowActivityListPresenter showActivityListPresenter = new ShowActivityListPresenter(this.getDashboardUI());
        Interactor showActivityInteractor = new Interactor(showActivityListPresenter);
        showActivity = new ShowActivity(showActivityInteractor);

        frame = new JFrame("Healthy4You Add Activity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(530, 1100);
        frame.setBackground(bgcolor);

        mainpanel = new JPanel();
        mainpanel.setBackground(bgcolor);
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));

        //-----------------------------top panel-----------------------------

        // Panel for back button with left alignment
        toppanel = new JPanel();
        toppanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        toppanel.setMaximumSize(new Dimension(400, 100));
        toppanel.setBackground(bgcolor);
        toppanel.setLayout(new BoxLayout(toppanel, BoxLayout.Y_AXIS));

        //loginlabel setup
        headinglabel = new JLabel("Add Activity");
        headinglabel.setFont(largefont);
        headinglabel.setForeground(headingcolor);
        headinglabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //toppanel component
        toppanel.add(Box.createVerticalGlue());
        toppanel.add(headinglabel);
        toppanel.add(Box.createVerticalGlue());

        //-----------------------------activityName panel-----------------------------

        //activityNamepanel setup
        activityNamepanel = new JPanel();
        activityNamepanel.setMaximumSize(new Dimension(400, 100));
        activityNamepanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        activityNamepanel.setBackground(bgcolor);
        activityNamepanel.setLayout(new BoxLayout(activityNamepanel, BoxLayout.Y_AXIS));

        //activityNamelabel setup
        activityNamelabel = new JLabel("Activity Name");
        activityNamelabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        activityNamelabel.setFont(mediumfont);
        activityNamelabel.setForeground(textcolor);

        //activityNameField setup
        activityNameField = new JTextField();
        activityNameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        activityNameField.setMaximumSize(new Dimension(400, 60));
        activityNameField.setPreferredSize(new Dimension(400, 60));
        activityNameField.setFont(mediumfont);
        activityNameField.setCaretColor(headingcolor);
        activityNameField.setForeground(headingcolor);
        activityNameField.setBackground(bgcolor);
        activityNameField.setBorder(new CompoundBorder(line, margin));

        //activityNamepanel component
        activityNamepanel.add(Box.createVerticalGlue());
        activityNamepanel.add(activityNamelabel);
        activityNamepanel.add(Box.createVerticalStrut(10));
        activityNamepanel.add(activityNameField);
        activityNamepanel.add(Box.createVerticalGlue());

        //-----------------------------activityTimeDuration panel-----------------------------

        //activityTimeDurationpanel setup
        activityTimeDurationpanel = new JPanel();
        activityTimeDurationpanel.setMaximumSize(new Dimension(400, 100));
        activityTimeDurationpanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        activityTimeDurationpanel.setBackground(bgcolor);
        activityTimeDurationpanel.setLayout(new BoxLayout(activityTimeDurationpanel, BoxLayout.Y_AXIS));

        //activityTimeDurationlabel setup
        activityTimeDurationlabel = new JLabel("Activity TimeDuration");
        activityTimeDurationlabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        activityTimeDurationlabel.setFont(mediumfont);
        activityTimeDurationlabel.setForeground(textcolor);

        //activityTimeDurationField setup
        activityTimeDurationField = new JTextField();
        activityTimeDurationField.setAlignmentX(Component.LEFT_ALIGNMENT);
        activityTimeDurationField.setMaximumSize(new Dimension(400, 60));
        activityTimeDurationField.setPreferredSize(new Dimension(400, 60));
        activityTimeDurationField.setFont(mediumfont);
        activityTimeDurationField.setCaretColor(headingcolor);
        activityTimeDurationField.setForeground(headingcolor);
        activityTimeDurationField.setBackground(bgcolor);
        activityTimeDurationField.setBorder(new CompoundBorder(line, margin));

        //activityTimeDurationpanel component
        activityTimeDurationpanel.add(Box.createVerticalGlue());
        activityTimeDurationpanel.add(activityTimeDurationlabel);
        activityTimeDurationpanel.add(Box.createVerticalStrut(10));
        activityTimeDurationpanel.add(activityTimeDurationField);
        activityTimeDurationpanel.add(Box.createVerticalGlue());

        //-----------------------------activityStartTime panel-----------------------------
        //activityStartTimepanel setup
        activityStartTimepanel = new JPanel();
        activityStartTimepanel.setMaximumSize(new Dimension(400, 100));
        activityStartTimepanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        activityStartTimepanel.setBackground(bgcolor);
        activityStartTimepanel.setLayout(new BoxLayout(activityStartTimepanel, BoxLayout.Y_AXIS));
        
        //subactivityStartTimepanel setup
        subactivityStartTimepanel = new JPanel();
        subactivityStartTimepanel.setMaximumSize(new Dimension(400, 60));
        subactivityStartTimepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        subactivityStartTimepanel.setBackground(bgcolor);
        subactivityStartTimepanel.setLayout(new BoxLayout(subactivityStartTimepanel, BoxLayout.X_AXIS));

        //activityStartTimelabel setup
        activityStartTimelabel = new JLabel("Activity StartTime");
        activityStartTimelabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        activityStartTimelabel.setFont(mediumfont);
        activityStartTimelabel.setForeground(textcolor);

        // Define the Text Fields for year, month, day, hour, and minute
        yearField = new JTextField(4);   // Year
        monthField = new JTextField(2);  // Month
        dayField = new JTextField(2);    // Day
        hourField = new JTextField(2);   // Hour
        minuteField = new JTextField(2); // Minute

        // Define Labels for each text field
        JLabel yearLabel = new JLabel("Year");
        yearLabel.setFont(smallfont);
        yearLabel.setForeground(textcolor);
        JLabel monthLabel = new JLabel("Month");
        monthLabel.setFont(smallfont);
        monthLabel.setForeground(textcolor);
        JLabel dayLabel = new JLabel("Day");
        dayLabel.setFont(smallfont);
        dayLabel.setForeground(textcolor);
        JLabel hourLabel = new JLabel("Hour");
        hourLabel.setFont(smallfont);
        hourLabel.setForeground(textcolor);
        JLabel minuteLabel = new JLabel("Minute");
        minuteLabel.setFont(smallfont);
        minuteLabel.setForeground(textcolor);

        // Set common properties for the new text fields
        JTextField[] fields = {yearField, monthField, dayField, hourField, minuteField};
        for (JTextField field : fields) {
            field.setMaximumSize(new Dimension(60, 60)); // Adjust size as needed
            field.setPreferredSize(new Dimension(60, 30));
            field.setFont(mediumfont);
            field.setCaretColor(headingcolor);
            field.setForeground(headingcolor);
            field.setBackground(bgcolor);
            field.setBorder(new CompoundBorder(line, margin));
        }

        // Add the text fields with labels to the panel
        subactivityStartTimepanel.add(yearLabel);
        subactivityStartTimepanel.add(Box.createHorizontalStrut(2));
        subactivityStartTimepanel.add(yearField);
        subactivityStartTimepanel.add(Box.createHorizontalStrut(2));
        subactivityStartTimepanel.add(monthLabel);
        subactivityStartTimepanel.add(Box.createHorizontalStrut(2));
        subactivityStartTimepanel.add(monthField);
        subactivityStartTimepanel.add(Box.createHorizontalStrut(2));
        subactivityStartTimepanel.add(dayLabel);
        subactivityStartTimepanel.add(Box.createHorizontalStrut(2));
        subactivityStartTimepanel.add(dayField);
        subactivityStartTimepanel.add(Box.createHorizontalStrut(2));
        subactivityStartTimepanel.add(hourLabel);
        subactivityStartTimepanel.add(Box.createHorizontalStrut(2));
        subactivityStartTimepanel.add(hourField);
        subactivityStartTimepanel.add(Box.createHorizontalStrut(2));
        subactivityStartTimepanel.add(minuteLabel);
        subactivityStartTimepanel.add(Box.createHorizontalStrut(2));
        subactivityStartTimepanel.add(minuteField);

        //activityStartTimepanel component
        activityStartTimepanel.add(Box.createVerticalGlue());
        activityStartTimepanel.add(activityStartTimelabel);
        activityStartTimepanel.add(Box.createVerticalStrut(10));
        activityStartTimepanel.add(subactivityStartTimepanel);
        activityStartTimepanel.add(Box.createVerticalGlue());

        //-----------------------------create Button-----------------------------

        //createButton setup
        createButton = new JButton("Create Activity");
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.setHorizontalAlignment(SwingConstants.CENTER);
        createButton.setMaximumSize(new Dimension(400, 60));
        createButton.setFont(mediumfont);
        createButton.setForeground(headingcolor);
        createButton.setBackground(themecolor);
        createButton.setOpaque(true);
        createButton.setBorderPainted(false);
        createButton.setFocusPainted(false);
        createButton.addActionListener(this);

        //mainpanel component
        mainpanel.add(Box.createVerticalStrut(30));
        mainpanel.add(toppanel);
        mainpanel.add(Box.createVerticalStrut(20));
        mainpanel.add(activityNamepanel);
        mainpanel.add(Box.createVerticalStrut(20));
        mainpanel.add(activityTimeDurationpanel);
        mainpanel.add(Box.createVerticalStrut(20));
        mainpanel.add(activityStartTimepanel);
        mainpanel.add(Box.createVerticalStrut(50));
        mainpanel.add(createButton);

        frame.setContentPane(mainpanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            String activityName = activityNameField.getText().trim();

            // Check if any field is empty
            if (activityName.isEmpty() ||
                    activityTimeDurationField.getText().trim().isEmpty() ||
                    yearField.getText().trim().isEmpty() ||
                    monthField.getText().trim().isEmpty() ||
                    dayField.getText().trim().isEmpty() ||
                    hourField.getText().trim().isEmpty() ||
                    minuteField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
                return; // Do not proceed further
            }

            // Parsing integer values safely
            try {
                int activityTimeDuration = Integer.parseInt(activityTimeDurationField.getText().trim());
                int year = Integer.parseInt(yearField.getText().trim());
                int month = Integer.parseInt(monthField.getText().trim());
                int day = Integer.parseInt(dayField.getText().trim());
                int hour = Integer.parseInt(hourField.getText().trim());
                int minute = Integer.parseInt(minuteField.getText().trim());

                // Proceed with the rest of your logic
                AddActivityPresenter addActivityPresenter = new AddActivityPresenter(this);
                CreatActivityInteractor creatActivityInteractor = new CreatActivityInteractor(addActivityPresenter);
                CreateActivity createActivity = new CreateActivity(creatActivityInteractor);
                createActivity.setTime(year, month, day, hour, minute);
                createActivity.execute(activityName, activityTimeDuration);
                showActivity.execute();
                frame.dispose();
                dashboardUI.showFrame();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers in all fields.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public DashboardUI getDashboardUI() {
        return dashboardUI;
    }
}

