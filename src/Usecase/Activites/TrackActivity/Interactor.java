package Usecase.Activites.TrackActivity;

import domain.Activity;
import domain.LoggedUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Interactor implements Input{
    final Output output;
    final DAI dai;
    Interactor(Output out, DAI da){
        output = out;
        dai = da;
    }

    public void execute(){
        if (LoggedUser.getUser() == null){
            output.prepareFailView("User not found");
        } else {
            List<Activity> activities = new ArrayList<>(LoggedUser.getUser().getActivities());
            LocalDateTime time = dai.getTime();
            List<List<String>> currentActivities = new ArrayList<>();
            for (Activity activity: activities){
                if (time.isAfter(activity.getTime().minusHours(1L)) && !activity.isCompletedToday()){
                    String desctiption  = activity.getDescription();
                    //Integer duration = activity.getDuration();
                    String duration = activity.getDurationInHours(); // hours instead of minutes
                    Boolean completion = activity.isCompletedToday();
                    LocalDateTime activityTime = activity.getTime();
                    List<String> activityList = new ArrayList<>();
                    activityList.add(desctiption);
                    //activityList.add(duration.toString());
                    activityList.add(duration); // hours instead of minutes
                    activityList.add(completion.toString());
                    activityList.add(activityTime.toString());
                    currentActivities.add(activityList);
                }
            }
            output.prepareSuccessView(new OutputData(currentActivities));
        }
    }
}
