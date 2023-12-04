package DAOs.ActivitiesDAO;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import domain.Activity;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

public class ActivitiesDAO {
    public static String fileName = "src/activity.txt";
    public static String splitter = "&";
    public static String splitter2 = ",";
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Map<String, List<String>> LoadAllActivities() throws IOException {
        Map<String, List<String>> activities = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> parts = new ArrayList<>(Arrays.asList(line.split(splitter)));
                if (parts.size() >= 2) {
                    activities.put(parts.get(0), parts.subList(1,parts.size()));
                }
            }
        }
        return activities;
    }

    public boolean WriteAllActivities(Map<String, List<String>> users){
        // Rewrite the user data file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, List<String>> entry : users.entrySet()) {
                String activitiesString = "";
                for (String activities: entry.getValue()) {
                    activitiesString += splitter + activities;
                }
                writer.write(entry.getKey() + splitter + activitiesString + "\n");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
            // Handle the exception appropriately
        }
    }
}
