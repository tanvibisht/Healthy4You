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

    public void complete(){
        isCompletedToday = false;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }
}
