package Sleep;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Sleep {
    private Map<String, Map<String, Double>> userSleepData; // Map of users to their sleep data map
    private static final String SLEEP_FILE_NAME = "src/sleep.txt";
    private String currentUsername;

    public Sleep() {
        userSleepData = new HashMap<>();
        currentUsername = readCurrentUsername();
        loadSleepData();
    }

    public void addSleepData(String day, double hours) {
        Map<String, Double> sleepData = userSleepData.getOrDefault(currentUsername, new HashMap<>());
        sleepData.put(day, hours);
        userSleepData.put(currentUsername, sleepData);
        saveSleepData();
    }

    public void clearSleepData() {
        userSleepData.put(currentUsername, new HashMap<>()); // Clearing data for the current user
        saveSleepData();
    }

    private void loadSleepData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SLEEP_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0];
                    String day = parts[1];
                    double hours = Double.parseDouble(parts[2]);
                    userSleepData.computeIfAbsent(username, k -> new HashMap<>()).put(day, hours);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveSleepData() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(SLEEP_FILE_NAME)))) {
            for (Map.Entry<String, Map<String, Double>> userEntry : userSleepData.entrySet()) {
                String username = userEntry.getKey();
                for (Map.Entry<String, Double> sleepEntry : userEntry.getValue().entrySet()) {
                    out.println(username + "," + sleepEntry.getKey() + "," + sleepEntry.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String readCurrentUsername() {
        try (BufferedReader reader = new BufferedReader(new FileReader("currentusername.txt"))) {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle appropriately
        }
    }

    public Map<String, Double> getUserSleepData() {
        return userSleepData.getOrDefault(currentUsername, new HashMap<>());
    }
}
