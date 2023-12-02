package Usecase.Activites.UpdateDatabaseActivities;

import domain.Activity;
import domain.LoggedUser;
import domain.User;

import java.util.ArrayList;
import java.util.List;

public class Interactor implements Input{
    final Output output;
    final DAI dataAccess;

    Interactor(Output out, DAI dai){
        output = out;
        dataAccess = dai;
    }

    public void execute(){
        User user = LoggedUser.getUser();
        if (user == null){
            output.prepareFailView("User not found");
        } else{
            List<Activity> activities = dataAccess.getActivities();
            List<Activity> memoryActivities = new ArrayList<>(user.getActivities());
            for (Activity activity: activities){
                if (!memoryActivities.contains(activity)){
                    user.addActivity(activity);
                }
            }
            output.prepareSuccessView("Activities Updated");
        }
    }
}
