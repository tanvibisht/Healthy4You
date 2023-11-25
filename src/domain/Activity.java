package domain;

public class Activity {
    private String description;
    private boolean isCompletedToday;
    private int duration; // Duration in minutes

    public Activity(String description, int duration) {
        this.description = description;
        this.duration = duration;
        this.isCompletedToday = false;
    }

    // Getters and setters for description, isCompletedToday, and duration
    // Add any other relevant methods as needed
}
