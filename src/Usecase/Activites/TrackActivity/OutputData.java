package Usecase.Activites.TrackActivity;

import java.time.LocalDateTime;
import java.util.List;

public class OutputData {
    private List<List<String>> activities;
    OutputData(List<List<String>> acts){
        activities = acts;
    }

    public List<List<String>> getActivities() {
        return activities;
    }
}
