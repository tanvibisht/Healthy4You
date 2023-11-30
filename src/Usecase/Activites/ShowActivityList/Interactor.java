package Usecase.Activites.ShowActivityList;

import domain.Activity;
import domain.LoggedUser;
import domain.User;

import java.util.ArrayList;
import java.util.List;

public class Interactor implements Input{
    final Output output;

    Interactor(Output out){
        output = out;
    }
    public void excute(){
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
                List<String> activityList = new ArrayList<>();
                activityList.add(desctiption);
                activityList.add(duration.toString());
                activityList.add(completion.toString());
                activitiesList.add(activityList);
            }
            output.prepareSuccessView(new OutputData(activitiesList));
        }
    }
}
