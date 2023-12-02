package Usecase.Activites.ShowActivityList;

import domain.Activity;
import domain.LoggedUser;
import domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Interactor implements Input{
    final Output output;

    Interactor(Output out){
        output = out;
    }
    public void execute(){
        User user = LoggedUser.getUser();
        if (user == null){
            output.prepareFailView("user not found");
        } else {
            List<List<String>> activitiesList = new ArrayList<>();
            List<Activity> activities = LoggedUser.getUser().getActivities();
            for (Activity activity: activities){
                String desctiption  = activity.getDescription();
                Integer duration = activity.getDuration();
                Boolean completion = activity.isCompletedToday();
                LocalDateTime time = activity.getTime();
                List<String> activityList = new ArrayList<>();
                activityList.add(desctiption);
                activityList.add(duration.toString());
                activityList.add(completion.toString());
                activityList.add(time.toString());
                activitiesList.add(activityList);
            }
            output.prepareSuccessView(new OutputData(activitiesList));
        }
    }
}
