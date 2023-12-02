package Usecase.Activites.CompleteActivity;

import domain.Activity;
import domain.LoggedUser;
import domain.User;


public class Interactor implements Input{
    final Output output;
    
    Interactor(Output out){
        output = out;
    }
    public void execute(InputData userData) {
        User user = LoggedUser.getUser();
        int index = userData.getActivity();
        if (user == null) {
            output.prepareFailView("User not Found");
        } else {
            Activity activity = user.compeleteActivity(index);
            String description = activity.getDescription();
            output.prepareSuccessView(new OutputData("Completed" + description));
        }
    }
}