package Usecase.Activites.UpdateDatabaseActivities;

import domain.Activity;
import domain.LoggedUser;
import domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Interactor implements Input{
    final Output output;
    final DAI dataAccess;

    public Interactor(Output out, DAI dai){
        output = out;
        dataAccess = dai;
    }

    public void execute(){
        User user = LoggedUser.getUser();
        if (user == null){
            output.prepareFailView("User not found");
        } else{
            List<Activity> activities = null;
            try {
                activities = dataAccess.getActivities(user);
                List<Activity> memoryActivities = new ArrayList<>(user.getActivities());
                for (Activity activity: activities){
                    if (!memoryActivities.contains(activity)){
                        user.addActivity(activity);
                    }
                }
                output.prepareSuccessView("Activities Updated");
            } catch (IOException e) {
                output.prepareFailView("Cannot read data");
            }
        }
    }
}
