package Hydration;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Hydration {
    private Map<String, Double> userHydrationData;
    private static final String FILE_NAME = "hydration.txt";

    public Hydration() {
        userHydrationData = new HashMap<>();
        loadHydrationData();
    }
    public void clearData(String username) {
        userHydrationData.put(username, 0.0);
        saveHydrationData();
    }
    public void addWater(String username, double liters) {
        userHydrationData.put(username, userHydrationData.getOrDefault(username, 0.0) + liters);
        saveHydrationData();
    }

    public double getTotalLiters(String username) {
        return userHydrationData.getOrDefault(username, 0.0);
    }

    private void loadHydrationData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    userHydrationData.put(parts[0], Double.parseDouble(parts[1]));
                }
            }
        } catch (IOException e) {
            // Handle exceptions or create a new file if it doesn't exist
        }
    }

    private void saveHydrationData() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
            for (Map.Entry<String, Double> entry : userHydrationData.entrySet()) {
                out.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (IOException e) {
            // Handle exceptions
        }
    }
}
