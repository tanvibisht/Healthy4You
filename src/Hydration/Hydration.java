package Hydration;

import java.io.*;
import java.util.*;

public class Hydration {
    private Map<String, Double> userHydrationData;
    private static final String FILE_NAME = "src/hydration.txt";

    public Hydration() {
        userHydrationData = new HashMap<>();
        String currentUsername = readCurrentUsername();
        loadHydrationData();
    }
    public void clearData(String username) {
        userHydrationData.put(username, 0.0);
        saveHydrationData();
    }
    String readCurrentUsername() {
        try (BufferedReader reader = new BufferedReader(new FileReader("currentusername.txt"))) {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle appropriately
        }
    }

    public void addWater(String username, double liters) {
        userHydrationData.put(username, userHydrationData.getOrDefault(username, 0.0) + liters);
        saveHydrationData();
    }

    private void loadHydrationData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Updated delimiter to comma
                if (parts.length == 2) {
                    userHydrationData.put(parts[0], Double.parseDouble(parts[1]));
                }
            }
        } catch (FileNotFoundException e) {
            // If the file doesn't exist, a new one will be created on save.
            System.err.println("Hydration data file not found. A new file will be created.");
        } catch (IOException e) {
            System.err.println("Error reading hydration data: " + e.getMessage());
        }
    }

    private void saveHydrationData() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
            for (Map.Entry<String, Double> entry : userHydrationData.entrySet()) {
                out.println(entry.getKey() + "," + entry.getValue()); // Save with a comma delimiter
            }
        } catch (IOException e) {
            System.err.println("Error saving hydration data: " + e.getMessage());
        }
    }

    public List<Double> getUserHydrationData(String username) {
        List<Double> data = new ArrayList<>();
        Double totalLiters = userHydrationData.get(username);
        if (totalLiters != null) {
            data.add(totalLiters);
        }
        return data;
    }

    public String getTotalLiters(String username) {
        if (userHydrationData.containsKey(username)) {
            // If present, return the total liters, formatted as a string
            return String.format("%.2f", userHydrationData.get(username));
        } else {
            // If not present, it means the user has no recorded hydration data
            return "0.00"; // Return "0.00" indicating no water has been recorded
        }
    }
}
