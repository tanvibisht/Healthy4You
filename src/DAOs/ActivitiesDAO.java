package DAOs;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import domain.Activity;

import java.io.*;
import java.util.*;

public class ActivitiesDAO {
    public static String fileName = "activity";
    public static String splitter = ":";
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

    public boolean WriteAllActivities(Map<String, List<String>> activities){
        return false;
    }
}
