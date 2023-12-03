package Usecase.Activites.CreateActivity;

import Usecase.Activites.CompleteActivity.OutputData;
import domain.Activity;
import domain.LoggedUser;
import domain.User;

import java.time.LocalDateTime;

public class CreatActivityInteractor implements CreatActivityInput{
    final CreatActivityOutput output;

    public CreatActivityInteractor(CreatActivityOutput out){
        output = out;
    }

    public void execute(CreatActivityInputData userData){
        User user = LoggedUser.getUser();
        String description = userData.getDescription();
        LocalDateTime time = userData.getTime();
        int duration = userData.getDuration();
        Activity activity = new Activity(description, duration, time);
        if (user == null){
            output.prepareFailView("User not Found");
        } else {
            user.addActivity(activity);
            output.prepareSuccessView(new CreatActivityOutputData("Success"));
        }
    }
}
