package Hydration;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HydrationGraphUI {
    private JFrame frame;
    private Hydration hydrationService;
    private String username;
    private XYChart chart;

    public HydrationGraphUI(Hydration hydrationService, String username) {
        this.hydrationService = hydrationService;
        this.username = username;

        frame = new JFrame("Hydration Tracker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize chart here, even if it might be empty initially
        List<Double> hydrationData = hydrationService.getUserHydrationData(username);
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
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Hydration Data").xAxisTitle("Day").yAxisTitle("Liters").build();

        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setXAxisMin(0.0);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setAxisTitlesVisible(true);
        chart.getStyler().setPlotGridLinesVisible(true);

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
