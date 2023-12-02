package domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String location;
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

    public void addActivity(Activity act) {
        int i  = 0;
        while (i < activities.size() && act.getTime().isBefore(activities.get(i).getTime())){
            i += 1;
        }
        activities.add(i + 1, act);
    }

    public Activity compeleteActivity(int index){
        Activity activity = activities.get(index);
        activity.complete();
        return activity;
    }

    public Activity deleteActivity(int index){
        Activity activity = activities.get(index);
        activities.remove(index);
        return activity;
    }

    // Additional methods as needed
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}


