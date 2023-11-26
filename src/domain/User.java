package domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Activity> activities;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.activities = new ArrayList<>();
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    // Additional methods as needed
}


