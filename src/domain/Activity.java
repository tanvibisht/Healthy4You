package domain;

import java.util.Objects;

public class Activity{
    private String description;
    private boolean isCompletedToday;
    private int duration; // Duration in minutes

    public Activity(String description, int duration) {
        this.description = description;
        this.duration = duration;
        this.isCompletedToday = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(description, activity.description);
    }

    public void complete(){
        isCompletedToday = false;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isCompletedToday() {
        return isCompletedToday;
    }
}
