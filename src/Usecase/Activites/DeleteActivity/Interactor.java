package Usecase.Activites.DeleteActivity;
import domain.Activity;
import domain.LoggedUser;
import domain.User;

public class Interactor implements Input{
    final Output output;

    public Interactor(Output out){
        output = out;
    }
    public void execute(InputData userData) {
        User user = LoggedUser.getUser();
        int index = userData.getActivity();
        if (user == null) {
            output.prepareFailView("User not Found");
        } else {
            Activity activity = user.deleteActivity(index);
            String description = activity.getDescription();
            output.prepareSuccessView(new OutputData("Completed" + description));
        }
    }
}
