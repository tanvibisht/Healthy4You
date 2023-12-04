package Sleep;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SleepUI {
    private JFrame frame;
    private Sleep sleepService;
    private String currentUsername;
    private XYChart chart;

    public SleepUI(Sleep sleepService) {
        this.sleepService = sleepService;
        this.currentUsername = sleepService.readCurrentUsername();

        frame = new JFrame("Sleep Tracker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize chart here, even if it might be empty initially
        Map<String, Double> sleepData = sleepService.getUserSleepData();
        chart = createChart(sleepData);
        frame.add(new XChartPanel<>(chart), BorderLayout.CENTER);

        addControlPanel();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addControlPanel() {
        JPanel controlPanel = new JPanel();
        JButton addHoursButton = new JButton("Add Sleep Hours");
        JButton clearDataButton = new JButton("Clear Data");

        addHoursButton.addActionListener(e -> addSleepHours());
        clearDataButton.addActionListener(e -> clearData());

        controlPanel.add(addHoursButton);
        controlPanel.add(clearDataButton);

        frame.add(controlPanel, BorderLayout.SOUTH);
    }

    private void addSleepHours() {
        String hoursString = JOptionPane.showInputDialog(frame, "Enter sleep hours:");
        if (hoursString != null && !hoursString.isEmpty()) {
            try {
                double hours = Double.parseDouble(hoursString);
                sleepService.addSleepData("Day X", hours); // Replace "Day X" with actual day identifier
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
        List<Integer> days = new ArrayList<>();
        List<Double> hours = new ArrayList<>();
        int day = 1;
        for (Map.Entry<String, Double> entry : sleepData.entrySet()) {
            days.add(day++);
            hours.add(entry.getValue());
        }

        if (chart != null) {
            chart.updateXYSeries("Sleep", days, hours, null);
            frame.revalidate(); // re-lay out the components
            frame.repaint();    // repaint the frame and its components
        }
    }

    private XYChart createChart(Map<String, Double> sleepData) {
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Sleep Data").xAxisTitle("Day").yAxisTitle("Hours").build();

        // Set a fixed range for the X-axis and Y-axis
        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setYAxisMax(24.0); // Assuming max 24 hours of sleep
        chart.getStyler().setXAxisMin(0.0);
        chart.getStyler().setXAxisMax(30.0); // To show at least 30 days

        // Adjust X-axis interval to show every day up to 30 days
        chart.getStyler().setXAxisTickMarkSpacingHint(1);

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setAxisTitlesVisible(true);
        chart.getStyler().setPlotGridLinesVisible(true);

        // Prepare the series data
        List<Integer> days = new ArrayList<>();
        List<Double> hours = new ArrayList<>();
        int day = 1;
        for (Map.Entry<String, Double> entry : sleepData.entrySet()) {
            days.add(day++);
            hours.add(entry.getValue());
        }
        // Ensure there is data for each day, even if it's zero
        while (days.size() < 30) {
            days.add(day++);
            hours.add(0.0);
        }

        // Add a series for a line graph
        XYSeries series = chart.addSeries("Sleep", days, hours);
        series.setMarker(SeriesMarkers.CIRCLE);

        return chart;
    }
}
