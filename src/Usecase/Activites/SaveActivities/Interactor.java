package Usecase.Activites.SaveActivities;

import domain.LoggedUser;
import domain.User;

public class Interactor implements Input {
    final Output output;
    final DAI dai;

    public Interactor(Output saveActivitiesOutputBoundary, DAI da){
        output = saveActivitiesOutputBoundary;
        dai = da;
    }

    @Override
    public void execute() {
        User user = LoggedUser.getUser();
        if (user == null){
            output.prepareFailView("User not found");
        } else {
            if (!dai.saveActivities(user)){
                output.prepareFailView("User not found");
            } else {
                output.prepareSuccessView("Saved");
            }
        }
    }
}
