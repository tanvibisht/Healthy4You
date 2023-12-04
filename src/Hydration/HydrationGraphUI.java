package Hydration;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class HydrationGraphUI {
    private JFrame frame;
    private Hydration hydrationService;
    private String currentUsername;
    private XYChart chart;

    public HydrationGraphUI(Hydration hydrationService) {
        this.hydrationService = hydrationService;
        this.currentUsername = hydrationService.readCurrentUsername();


        frame = new JFrame("Hydration Tracker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize chart here, even if it might be empty initially
        List<Double> hydrationData = hydrationService.getUserHydrationData(currentUsername);
        chart = createChart(hydrationData);
        frame.add(new XChartPanel<>(chart), BorderLayout.CENTER);

        addControlPanel();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addControlPanel() {
        JPanel controlPanel = new JPanel();
        JButton addLitersButton = new JButton("Add Liters");
        JButton clearDataButton = new JButton("Clear Data");

        addLitersButton.addActionListener(e -> addLiters());
        clearDataButton.addActionListener(e -> clearData());

        controlPanel.add(addLitersButton);
        controlPanel.add(clearDataButton);

        frame.add(controlPanel, BorderLayout.SOUTH);
    }

    private void addLiters() {
        String litersString = JOptionPane.showInputDialog(frame, "Enter water amount (liters):");
        if (litersString != null && !litersString.isEmpty()) {
            try {
                double liters = Double.parseDouble(litersString);
                hydrationService.addWater(currentUsername, liters);
                updateChart();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            }
        }
    }

    private void clearData() {
        hydrationService.clearData(currentUsername);
        updateChart();
    }

    private void updateChart() {
        List<Double> hydrationData = hydrationService.getUserHydrationData(currentUsername);
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
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Hydration Data").xAxisTitle("Day").yAxisTitle("Liters").build();

        // Set a fixed range for the X-axis and Y-axis
        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setYAxisMax(10.0); // Assuming you want to show up to 10 liters
        chart.getStyler().setXAxisMin(0.0);
        chart.getStyler().setXAxisMax(10.0); // To show at least 10 days

        // Adjust X-axis interval to show every day up to 10 days
        chart.getStyler().setXAxisTickMarkSpacingHint(1);

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setAxisTitlesVisible(true);
        chart.getStyler().setPlotGridLinesVisible(true);

        // Prepare the series data
        List<Integer> days = new ArrayList<>();
        for (int i = 1; i <= 10; i++) { // Ensure there are at least 10 days
            days.add(i);
        }
        // Ensure there is data for each day, even if it's zero
        while (hydrationData.size() < 10) {
            hydrationData.add(0.0);
        }

        // Add a series for a line graph
        XYSeries series = chart.addSeries("Hydration", days, hydrationData);
        series.setMarker(SeriesMarkers.CIRCLE);

        return chart;
    }



}