package Usecase.Activites.ShowActivityList;

import java.util.List;

public class OutputData {
    private final List<List<String>> activities;

    OutputData(List<List<String>> acts){
        activities = acts;
    }

    public List<List<String>> getActivities() {
        return activities;
    }
}
