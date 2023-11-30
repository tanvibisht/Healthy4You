package Usecase.Activites.CreateActivity;

import Usecase.Activites.CompleteActivity.OutputData;
import domain.Activity;
import domain.LoggedUser;
import domain.User;

public class CreatActivityInteractor implements CreatActivityInput{
    final CreatActivityOutput output;

    CreatActivityInteractor(CreatActivityOutput out){
        output = out;
    }

    public void excute(CreatActivityInputData userData){
        User user = LoggedUser.getUser();
        String description = userData.getDescription();
        int duration = userData.getDuration();
        Activity activity = new Activity(description, duration);
        if (user == null){
            output.prepareFailView("User not Found");
        } else {
            user.addActivity(activity);
            output.prepareSuccessView(new CreatActivityOutputData("Success"));
        }
    }
}
