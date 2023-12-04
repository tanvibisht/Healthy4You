package Hydration;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import service.UserService;
import ui.DashboardUI;
import ui.LoginUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class HydrationGraphUI {
    private JFrame frame;
    private JPanel mainpanel;
    private Hydration hydrationService;
    private UserService userService;
    private String username;
    private XYChart chart;
    private Color bgcolor = new Color(41, 41, 41);
    private Color themecolor = new Color(143, 88, 178);
    private Color headingcolor = new Color(255, 255, 255);
    private Color textcolor = new Color(156, 156, 156);
    private Color boxcolor = new Color(100, 80, 150);
    private Font largefont = new Font("Monospaced", Font.BOLD, 30);
    private Font mediumfont = new Font("Monospaced", Font.BOLD, 16);
    private Font smallfont = new Font("Monospaced", Font.BOLD, 12);

    public HydrationGraphUI(Hydration hydrationService, String username, UserService userService) {
        this.hydrationService = hydrationService;
        this.username = username;
        this.userService = userService;

        frame = new JFrame("Hydration Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(530, 1100);
        frame.setBackground(bgcolor);

        mainpanel = new JPanel();
        mainpanel.setBackground(bgcolor);
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));

        // Initialize chart here, even if it might be empty initially
        List<Double> hydrationData = hydrationService.getUserHydrationData(username);
        chart = createChart(hydrationData);
        XChartPanel<XYChart> chartPanel = new XChartPanel<>(chart);
        chartPanel.setPreferredSize(new Dimension(470, 350)); // Update this line with the new size
        chartPanel.setMaximumSize(chartPanel.getPreferredSize());
        chartPanel.setMaximumSize(chartPanel.getPreferredSize());
        chartPanel.setSize(new Dimension(470, 350));
        chartPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //mainpanel component
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
        subcontrolPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30,0));

        JButton addLitersButton = new JButton("Add Liters");
        addLitersButton.setMaximumSize(new Dimension(220, 60));
        addLitersButton.setPreferredSize(new Dimension(220, 60));
        addLitersButton.setFont(mediumfont);
        addLitersButton.setForeground(headingcolor);
        addLitersButton.setBackground(boxcolor);
        addLitersButton.setFocusPainted(false);
        addLitersButton.setBorderPainted(false);
        addLitersButton.setOpaque(true);
        addLitersButton.setContentAreaFilled(true);
        addLitersButton.addActionListener(e -> addLiters());

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

    private void addLiters() {
        String litersString = JOptionPane.showInputDialog(frame, "Enter water amount (liters):");
        if (litersString != null && !litersString.isEmpty()) {
            try {
                double liters = Double.parseDouble(litersString);
                hydrationService.addWater(username, liters);
                updateChart();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            }
        }
    }

    private void clearData() {
        hydrationService.clearData(username);
        updateChart();
    }

    private void updateChart() {
        List<Double> hydrationData = hydrationService.getUserHydrationData(username);
        List<Integer> days = new ArrayList<>();
        for (int i = 0; i < hydrationData.size(); i++) {
            days.add(i + 1);
        }

        if (chart != null) {
            chart.updateXYSeries("Hydration", days, hydrationData, null);
            frame.revalidate(); // re-lay out the components
            frame.repaint();    // repaint the frame and its components
        }
    }

    private XYChart createChart(List<Double> hydrationData) {
        XYChart chart = new XYChartBuilder().width(470).height(350).title("Hydration Data").xAxisTitle("Day").yAxisTitle("Liters").build();

        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setXAxisMin(0.0);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setAxisTitlesVisible(true);
        chart.getStyler().setPlotGridLinesVisible(true);

        // Customize chart colors
        chart.getStyler().setChartBackgroundColor(bgcolor);
        chart.getStyler().setPlotBackgroundColor(bgcolor);
        chart.getStyler().setPlotBorderColor(themecolor);
        chart.getStyler().setSeriesColors(new Color[]{themecolor});
        chart.getStyler().setAxisTickLabelsColor(textcolor);
        chart.getStyler().setXAxisTitleColor(textcolor);
        chart.getStyler().setChartFontColor(textcolor);

        // If hydrationData is empty, initialize it with default values to avoid IllegalArgumentException
        if (hydrationData.isEmpty()) {
            hydrationData.add(0.0); // default value
        }
        List<Integer> days = new ArrayList<>();
        for (int i = 1; i <= hydrationData.size(); i++) {
            days.add(i);
        }

        // Add a series for a line graph
        XYSeries series = chart.addSeries("Hydration", days, hydrationData);
        series.setMarker(SeriesMarkers.CIRCLE);

        return chart;
    }

}
