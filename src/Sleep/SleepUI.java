package Sleep;

import Hydration.Hydration;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import service.UserService;
import ui.DashboardUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.*;
import java.util.List;

public class SleepUI {
    private JFrame frame;
    private JPanel mainpanel;
    private JPanel toppanel;
    private JLabel headinglabel;
    private Sleep sleepService;
    private XYChart chart;
    private DashboardUI dashboardUI;
    private XChartPanel<XYChart> chartPanel;  // Add this field
    private Color bgcolor = new Color(41, 41, 41);
    private Color themecolor = new Color(143, 88, 178);
    private Color headingcolor = new Color(255, 255, 255);
    private Color textcolor = new Color(156, 156, 156);
    private Color boxcolor = new Color(100, 80, 150);
    private Font largefont = new Font("Monospaced", Font.BOLD, 30);
    private Font mediumfont = new Font("Monospaced", Font.BOLD, 16);
    private Font smallfont = new Font("Monospaced", Font.BOLD, 12);

    public SleepUI(Sleep sleepService, DashboardUI dashboardUI) {
        this.sleepService = sleepService;
        this.dashboardUI = dashboardUI;

        frame = new JFrame("Sleep Tracker");
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
        headinglabel = new JLabel("Sleep Schedule");
        headinglabel.setFont(largefont);
        headinglabel.setForeground(headingcolor);
        headinglabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //toppanel component
        toppanel.add(Box.createVerticalGlue());
        toppanel.add(headinglabel);
        toppanel.add(Box.createVerticalGlue());

        // Initialize chart here, even if it might be empty initially
        Map<String, Double> sleepData = sleepService.getUserSleepData();
        chart = createChart(sleepData);
        chartPanel = new XChartPanel<>(chart);
        chartPanel.setPreferredSize(new Dimension(470, 350)); // Update this line with the new size
        chartPanel.setMaximumSize(chartPanel.getPreferredSize());
        chartPanel.setMaximumSize(chartPanel.getPreferredSize());
        chartPanel.setSize(new Dimension(470, 350));
        chartPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //mainpanel component
        mainpanel.add(Box.createVerticalStrut(80));
        mainpanel.add(toppanel);
        mainpanel.add(Box.createVerticalGlue());
        mainpanel.add(chartPanel);
        mainpanel.add(Box.createVerticalGlue());
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
        subcontrolPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        subcontrolPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));

        JButton addLitersButton = new JButton("Add Sleep Hours");
        addLitersButton.setMaximumSize(new Dimension(220, 60));
        addLitersButton.setPreferredSize(new Dimension(220, 60));
        addLitersButton.setFont(mediumfont);
        addLitersButton.setForeground(headingcolor);
        addLitersButton.setBackground(boxcolor);
        addLitersButton.setFocusPainted(false);
        addLitersButton.setBorderPainted(false);
        addLitersButton.setOpaque(true);
        addLitersButton.setContentAreaFilled(true);
        addLitersButton.addActionListener(e -> addSleepHours());

        JButton clearDataButton = new JButton("Clear Data");
        clearDataButton.setMaximumSize(new Dimension(220, 60));
        clearDataButton.setPreferredSize(new Dimension(220, 60));
        clearDataButton.setFont(mediumfont);
        clearDataButton.setForeground(headingcolor);
        clearDataButton.setBackground(boxcolor);
        clearDataButton.setFocusPainted(false);
        clearDataButton.setBorderPainted(false);
        clearDataButton.setOpaque(true);
        clearDataButton.setContentAreaFilled(true);
        clearDataButton.addActionListener(e -> clearData());

        subcontrolPanel.add(addLitersButton);
        subcontrolPanel.add(clearDataButton);

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
                dashboardUI.showFrame();
            }
        });

        controlPanel.add(Box.createVerticalGlue());
        controlPanel.add(subcontrolPanel);
        controlPanel.add(Box.createVerticalStrut(30));
        controlPanel.add(backButton);
        controlPanel.add(Box.createVerticalGlue());

        mainpanel.add(controlPanel);
    }

    private void addSleepHours() {
        String hoursString = JOptionPane.showInputDialog(frame, "Enter sleep hours for Day 1:");
        if (hoursString != null && !hoursString.isEmpty()) {
            try {
                double hours = Double.parseDouble(hoursString);
                sleepService.addSleepData(1, hours);  // Correctly adding to Day 1 as a string
                updateChart();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            }
        }
    }

    private void clearData() {
        sleepService.clearSleepData();
        updateChart();
    }

    private void updateChart() {
        Map<String, Double> sleepData = sleepService.getUserSleepData();
        chart = createChart(sleepData);

        // Remove the old chart panel and add the new one
        mainpanel.removeAll();
        mainpanel.add(Box.createVerticalStrut(80));
        mainpanel.add(toppanel);
        mainpanel.add(Box.createVerticalGlue());  // Add other components as needed
        XChartPanel<XYChart> chartPanel = new XChartPanel<>(chart);
        chartPanel.setPreferredSize(new Dimension(470, 350)); // Update this line with the new size
        chartPanel.setMaximumSize(chartPanel.getPreferredSize());
        chartPanel.setMaximumSize(chartPanel.getPreferredSize());
        chartPanel.setSize(new Dimension(470, 350));
        chartPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainpanel.add(chartPanel);
        mainpanel.add(Box.createVerticalGlue());  // Add other components as needed
        addControlPanel();  // This adds the control panel back to mainpanel
        mainpanel.add(Box.createVerticalStrut(20));

        // Refresh the frame
        frame.revalidate();
        frame.repaint();
    }


    private XYChart createChart(Map<String, Double> sleepData) {
        XYChart chart = new XYChartBuilder().width(470).height(350).title("Sleep Data").xAxisTitle("Day").yAxisTitle("Hours").build();

        // Set a fixed range for the X-axis and Y-axis
        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setYAxisMax(24.0); // Assuming max 24 hours of sleep
        chart.getStyler().setXAxisMin(0.0);
        chart.getStyler().setXAxisMax(30.0); // To show at least 30 days

        // Customize chart colors
        chart.getStyler().setChartBackgroundColor(bgcolor);
        chart.getStyler().setPlotBackgroundColor(bgcolor);
        chart.getStyler().setPlotBorderColor(themecolor);
        chart.getStyler().setSeriesColors(new Color[]{themecolor});
        chart.getStyler().setAxisTickLabelsColor(textcolor);
        chart.getStyler().setXAxisTitleColor(textcolor);
        chart.getStyler().setChartFontColor(textcolor);

        // Adjust X-axis interval to show every day up to 30 days
        chart.getStyler().setXAxisTickMarkSpacingHint(1);

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setAxisTitlesVisible(true);
        chart.getStyler().setPlotGridLinesVisible(true);

        List<Number> days = new ArrayList<>();
        List<Double> hours = new ArrayList<>();

        // Assuming we have 30 days of data
        for (int i = 1; i <= 30; i++) {
            days.add(i);
            hours.add(sleepData.getOrDefault("Day " + i, 0.0));
        }

        // Add a series for a line graph
        XYSeries series = chart.addSeries("Sleep", days, hours);
        series.setMarker(SeriesMarkers.CIRCLE);

        return chart;
    }
}
